package com.barclays.paymentsystem.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barclays.paymentsystem.constants.SystemConstants;
import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.BillStatus;
import com.barclays.paymentsystem.entity.Bills;
import com.barclays.paymentsystem.entity.RegisteredBiller;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.repository.AccountRepository;
import com.barclays.paymentsystem.repository.BillRepository;
import com.barclays.paymentsystem.repository.RegisteredBillerRespository;

/**
 * AutoPayBillServiceImpl - AutoPayBillService implementation class
 * 
 * @author Ved
 *
 */

@Service(value = "autoPayBillService")
@Transactional
public class AutoPayBillServiceImpl implements AutoPayBillService {

	@Autowired
	BillRepository billRepository;
	
	@Autowired
	RegisteredBillerRespository registeredBillerRespository;

	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public String autoPayBills() throws PaymentSystemException {
		List<Bills> pendingBills = billRepository.findByStatus(BillStatus.PENDING);

		if (pendingBills.size() == 0) return SystemConstants.NO_PENDING_BILL_RESPONSE;
		
		for(Bills pendingBill : pendingBills) {
			LocalDate today = LocalDate.now();
			Period dateDiff = Period.between(pendingBill.getDueDate(), today);
			
			if (dateDiff.getYears() == 0 && dateDiff.getMonths() == 0 && dateDiff.getDays() <= 3) {
				RegisteredBiller registeredBiller = registeredBillerRespository.findByConsumerNumberAndBillerCodeAndAccount(
					pendingBill.getConsumerNumber(), 
					pendingBill.getBillerCode(), 
					pendingBill.getAccount()
				);
								
				if (registeredBiller == null)
					throw new PaymentSystemException(SystemConstants.REGISTERED_BILLER_NOT_FOUND_REPONSE);
				
				if (!registeredBiller.getAutopay()) continue;
				
				System.out.println("fnlhflwhad");
				System.out.println("fnlhflwhad");
				
				if (registeredBiller.getAutopayLimit() == null) {
					payBill(pendingBill);
				} else if (registeredBiller.getAutopayLimit() != null && registeredBiller.getAutopayLimit() < pendingBill.getAmount()) {
					payBill(pendingBill);
				}
			}
		}
		
		return SystemConstants.PENDING_BILLS_SUCCESSFULLY_PAID_RESPONSE;
	}

	@Override
	public String payBill(Bills bill) throws PaymentSystemException {
		Account account = bill.getAccount();
		Double billAmount = bill.getAmount();		
		Double currentBalance = account.getCurrentBalance();
		
		if (currentBalance >= billAmount) {
			account.setCurrentBalance(currentBalance - billAmount);
			bill.setStatus(BillStatus.PAID);
			
			Bills newBill =  billRepository.save(bill);
			Account newAccount = accountRepository.save(account);
			
			if (newBill != null && newAccount != null) {

				
				return SystemConstants.BILL_PAYMENT_SUCCESS_RESPONSE;
				
				// TODO: send mail here
			} else {
				throw new PaymentSystemException(SystemConstants.BILL_PAYMENT_FAILURE_RESPONSE);				
			}
		} else {
			throw new PaymentSystemException(SystemConstants.INSUFFICENT_BALANCE_RESPONSE);
		}
	}
}
