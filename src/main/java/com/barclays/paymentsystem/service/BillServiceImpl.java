package com.barclays.paymentsystem.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.barclays.paymentsystem.constants.SystemConstants;
import com.barclays.paymentsystem.dto.AccountTransactionDTO;
import com.barclays.paymentsystem.dto.BillDTO;
import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.AccountTransaction;
import com.barclays.paymentsystem.entity.Bill;
import com.barclays.paymentsystem.entity.BillStatus;
import com.barclays.paymentsystem.entity.MasterBiller;
import com.barclays.paymentsystem.entity.RegisteredBiller;
import com.barclays.paymentsystem.entity.TransactionType;
import com.barclays.paymentsystem.entity.User;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.repository.AccountRepository;
import com.barclays.paymentsystem.repository.AccountTransactionRepository;
import com.barclays.paymentsystem.repository.BillRepository;
import com.barclays.paymentsystem.repository.MasterBillerRepository;
import com.barclays.paymentsystem.repository.RegisteredBillerRepository;
import com.barclays.paymentsystem.repository.UserRepository;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * BillServiceImpl - BIll service interface implementation
 * @author PB3C
 *
 */

@Service
@Transactional
public class BillServiceImpl implements BillService {

	private final Logger LOGGER = LoggerFactory.getLogger(getClass());
	
	@Autowired
	BillRepository billRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	MasterBillerRepository masterBillerRepository;
	
	@Autowired
	RegisteredBillerRepository registeredBillerRepository;
	
	@Autowired
	AccountTransactionRepository accountTransactionRepository;

    @Autowired
    private Configuration config;

    @Autowired
    private JavaMailSender mailSender;
    
    /**
	 * To find Bills
	 * @param username
	 * @return List of Bills
	 * @throws PaymentSystemException
	 */

	@Override
	public List<BillDTO> findAll(String username) throws PaymentSystemException {
		Optional<User> opt = userRepository.findById(username);

		if (!opt.isPresent())
			throw new PaymentSystemException(SystemConstants.USER_NOT_FOUND_RESPONSE);

		User user = opt.get();
		Account account = user.getAccount();

		List<Bill> list = billRepository.findByAccount(account);
		List<BillDTO> transactionList = new ArrayList<>();
		list.forEach(transaction -> transactionList.add(new BillDTO(transaction)));

		return transactionList;
	}
	
	/**
	 * To find Bills Between Specified Date range
	 * @param username,from,to
	 * @return List of Bills
	 * @throws PaymentSystemException
	 */

	@Override
	public List<BillDTO> findAllBetweenDate(String username, LocalDate from, LocalDate to)
			throws PaymentSystemException {
		Optional<User> opt = userRepository.findById(username);

		if (!opt.isPresent())
			throw new PaymentSystemException(SystemConstants.USER_NOT_FOUND_RESPONSE);

		User user = opt.get();
		Account account = user.getAccount();

		List<Bill> list = billRepository.findByAccountAndDueDateBetween(account, from, to);
		List<BillDTO> transactionList = new ArrayList<>();

		list.forEach(transaction -> transactionList.add(new BillDTO(transaction)));

		return transactionList;
	}
	
	/**
	 * @addNewBill
	 * @param billDTO
	 * @return consumer number of new bill
	 * @throws PaymentSystemException
	 */
	@Override
    public String addNewBill(BillDTO billDTO) throws PaymentSystemException {
		if (billDTO == null)
			throw new PaymentSystemException(SystemConstants.BILL_DATA_NOT_PROVIDED);
		
        Bill bills = billDTO.toEntity();
        Bill newBill= billRepository.save(bills);
        
        MasterBiller biller = masterBillerRepository.findById(newBill.getMasterBiller().getBillerCode()).get();
        
        Optional<Account> opt = accountRepository.findById(newBill.getAccount().getAccountNo());
        
        if (!opt.isPresent())
        	throw new PaymentSystemException(SystemConstants.BILL_PAYMENT_FAILURE_RESPONSE);
        
        Account account = opt.get();
        
        Map<String, Object> model = createMailModel(newBill, biller, account);

        sendEmail(model,"utils.ftl");
        return newBill.getConsumerNumber();
    }
	
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
			Period dateDiff = Period.between(today, pendingBill.getDueDate());
			
			if (dateDiff.getYears() == 0 && dateDiff.getMonths() == 0 && dateDiff.getDays() <= 3) {
				LOGGER.info("info: " + pendingBill.getConsumerNumber() + " " + pendingBill.getMasterBiller().getBillerCode() + " " + pendingBill.getAccount().getAccountNo());
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
				} else if (registeredBiller.getAutopayLimit() != null && registeredBiller.getAutopayLimit() > pendingBill.getAmount()) {
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
		
		if (bill == null)
			return SystemConstants.NO_PENDING_BILL_RESPONSE;
		
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
			LOGGER.info("Paying bill");
			account.setCurrentBalance(currentBalance - billAmount);
			bill.setStatus(BillStatus.PAID);
			
			Bill paidBill =  billRepository.save(bill);
			Account updatedAccount = accountRepository.save(account);
			AccountTransaction newTransaction = saveAccountTranscation(paidBill, description);
			
//			Bill nextBill = generateNextMonthBill(paidBill);
			
			if (paidBill != null && updatedAccount != null && newTransaction != null) {
				LOGGER.info("Sending mail");
				Map<String, Object> model = createMailModel(paidBill, bill.getMasterBiller(), account);
				sendEmail(model,"utils.ftl");
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
	
	/**
	 * @sendEmail
	 * @param model,path
	 */

    private void sendEmail(Map<String, Object> model,String path){
        MimeMessage message = mailSender.createMimeMessage();
        
        LOGGER.info(model.get("EmailID").toString());
        
        if(model.get("EmailID") != null && (model.get("EmailID").toString()).length()>0){
            try{
                MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                        StandardCharsets.UTF_8.name());
                Template t = config.getTemplate(path);
                String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

                helper.setFrom("vedpatil611@gmail.com");
                helper.setTo(model.get("EmailID").toString());
                helper.setText(html,true);
                helper.setSubject("Bill Payments - "+model.get("Name").toString());
                mailSender.send(message);
                System.out.print("Mail Send");
            }catch (Exception e) {
               e.printStackTrace();
            }
        }
    }
    
	private Map<String, Object> createMailModel(Bill newBill, MasterBiller biller, Account account) {
		Map<String, Object> model = new HashMap<>();
        model.put("BillCode", biller.getBillerCode());
        model.put("BillName", biller.getName());
        model.put("ConsumerNumber", newBill.getConsumerNumber());
        model.put("Amount", newBill.getAmount());
        model.put("DueDate", newBill.getDueDate());
        model.put("Status", newBill.getStatus());
        model.put("AccountNumber", account.getAccountNo());
        model.put("Name", account.getName());
        model.put("EmailID",account.getEmailId());
        model.put("CreationDate", LocalDateTime.now().toString());
		return model;
	}
}
