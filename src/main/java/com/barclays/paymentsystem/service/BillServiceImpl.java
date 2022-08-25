package com.barclays.paymentsystem.service;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.barclays.paymentsystem.constants.SystemConstants;
import com.barclays.paymentsystem.dto.BillDTO;
import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.Bill;
import com.barclays.paymentsystem.entity.MasterBiller;
import com.barclays.paymentsystem.entity.User;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.repository.AccountRepository;
import com.barclays.paymentsystem.repository.BillRepository;
import com.barclays.paymentsystem.repository.MasterBillerRepository;
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

	@Autowired
	BillRepository billRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Autowired
	MasterBillerRepository masterBillerRepository;

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

        sendEmail(model,"utils.ftl");
        return newBill.getConsumerNumber();
    }
	
	/**
	 * @sendEmail
	 * @param model,path
	 */

    private void sendEmail(Map<String, Object> model,String path){
        MimeMessage message = mailSender.createMimeMessage();
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
}
