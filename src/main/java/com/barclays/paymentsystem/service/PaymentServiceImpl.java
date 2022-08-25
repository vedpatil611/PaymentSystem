package com.barclays.paymentsystem.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barclays.paymentsystem.constants.SystemConstants;
import com.barclays.paymentsystem.dto.AccountTransactionDTO;
import com.barclays.paymentsystem.dto.BillDTO;
import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.AccountTransaction;
import com.barclays.paymentsystem.entity.BillStatus;
import com.barclays.paymentsystem.entity.Bill;
import com.barclays.paymentsystem.entity.RegisteredBiller;
import com.barclays.paymentsystem.entity.TransactionType;
import com.barclays.paymentsystem.entity.User;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.repository.AccountRepository;
import com.barclays.paymentsystem.repository.AccountTransactionRepository;
import com.barclays.paymentsystem.repository.BillRepository;
import com.barclays.paymentsystem.repository.RegisteredBillerRepository;
import com.barclays.paymentsystem.repository.UserRepository;

/**
 * PaymentServiceImpl - PaymentService implementation class
 * 
 * @author PB3C
 *
 */
@Service(value = "autoPayBillService")
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	BillRepository billRepository;
	
	@Autowired
	RegisteredBillerRepository registeredBillerRepository;

	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	AccountTransactionRepository accountTransactionRepository;
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * @autoPayBills
	 * @param null
	 * @return response of pending bills paid successfully
	 * @throws PaymentSystemException
	 */
	
	@Override
	public String autoPayBills() throws PaymentSystemException {
		List<Bill> pendingBills = billRepository.findByStatus(BillStatus.PENDING);

		if (pendingBills.size() == 0) return SystemConstants.NO_PENDING_BILL_RESPONSE;
		
		for(Bill pendingBill : pendingBills) {
			LocalDate today = LocalDate.now();
			Period dateDiff = Period.between(pendingBill.getDueDate(), today);
			
			if (dateDiff.getYears() == 0 && dateDiff.getMonths() == 0 && dateDiff.getDays() <= 3) {
				RegisteredBiller registeredBiller = registeredBillerRepository.findByConsumerNumberAndBillerCodeAndAccount(
					pendingBill.getConsumerNumber(), 
					pendingBill.getMasterBiller(), 
					pendingBill.getAccount()
				);
								
				if (registeredBiller == null)
					throw new PaymentSystemException(SystemConstants.REGISTERED_BILLER_NOT_FOUND_REPONSE);
				
				if (!registeredBiller.getAutopay()) continue;
				
				if (registeredBiller.getAutopayLimit() == null) {
					payBill(pendingBill, "Auto paid bill");
				} else if (registeredBiller.getAutopayLimit() != null && registeredBiller.getAutopayLimit() < pendingBill.getAmount()) {
					payBill(pendingBill, "Auto paid bill");
				}
			}
		}
		
		return SystemConstants.PENDING_BILLS_SUCCESSFULLY_PAID_RESPONSE;
	}
	
	/**
	 * @manuallyPayBill
	 * @param username
	 * @param billerCode
	 * @return Manually bill payment
	 * @throws PaymentSystemException
	 */

	@Override
	public String manuallyPayBill(String username, String billerCode) throws PaymentSystemException {
		Optional<User> opt = userRepository.findById(username);
		if (!opt.isPresent())
			throw new PaymentSystemException(SystemConstants.USER_NOT_FOUND_RESPONSE);
		
		User user = opt.get();
		Bill bill = billRepository.findByAccountAndMasterBiller_billerCodeAndStatus(user.getAccount(), billerCode, BillStatus.PENDING);
		
		return payBill(bill, "Manually paid bills");
	}
	
	/**
	 * Pay bill
	 * @param bill
	 * @param description
	 * @return
	 * @throws PaymentSystemException
	 */
	String payBill(Bill bill, String description) throws PaymentSystemException {
		Account account = bill.getAccount();
		Double billAmount = bill.getAmount();
		Double currentBalance = account.getCurrentBalance();
		
		if (currentBalance >= billAmount) {
			account.setCurrentBalance(currentBalance - billAmount);
			bill.setStatus(BillStatus.PAID);
			
			Bill paidBill =  billRepository.save(bill);
			Account newAccount = accountRepository.save(account);
			AccountTransaction newTransaction = saveAccountTranscation(paidBill, description);
			
//			Bill nextBill = generateNextMonthBill(paidBill);
			
			if (paidBill != null && newAccount != null && newTransaction != null) {
				// TODO: send mail for paid bill here (paidBill)

				// TODO: send mail for bill generated for next month (nextBill)
				
				return SystemConstants.BILL_PAYMENT_SUCCESS_RESPONSE;
			} else {
				throw new PaymentSystemException(SystemConstants.BILL_PAYMENT_FAILURE_RESPONSE);				
			}
		} else {
			throw new PaymentSystemException(SystemConstants.INSUFFICENT_BALANCE_RESPONSE);
		}
	}
	
	/**
	 * @generateNextMonthBill
	 * @param bill
	 * @return new bill of next month
	 * @throws PaymentSystemException
	 */
	
//	Bill generateNextMonthBill(Bill bill) throws PaymentSystemException {
//		BillDTO billDTO = new BillDTO(bill);
//		billDTO.setSequenceId(null);
//		billDTO.setDueDate(billDTO.getDueDate().plusMonths(1));
//		billDTO.setStatus(BillStatus.PENDING);
//		
//		Bill newBill = billDTO.toEntity();
//		newBill.setAccount(bill.getAccount());
//		newBill = billRepository.save(newBill);
//
//		return newBill;
//	}
//	
	/**
	 * Save transaction history
	 * @param bill
	 * @param description
	 * @return
	 */
	AccountTransaction saveAccountTranscation(Bill bill, String description) {
		AccountTransactionDTO accountTransactionDTO = new AccountTransactionDTO();
		accountTransactionDTO.setTransactionReference(bill.getSequenceId());
		accountTransactionDTO.setDateTime(LocalDateTime.now());
		accountTransactionDTO.setAmount(bill.getAmount());
		accountTransactionDTO.setDescription(description);
		accountTransactionDTO.setType(TransactionType.CREDITED);
		accountTransactionDTO.setRefNo(null);
		
		AccountTransaction accountTransaction = accountTransactionDTO.toEntity();
		AccountTransaction newTransaction = accountTransactionRepository.save(accountTransaction);
		newTransaction.setRefNo(bill);
		newTransaction = accountTransactionRepository.save(newTransaction);
		return newTransaction;
	}
}
