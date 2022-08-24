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
import com.barclays.paymentsystem.entity.Bills;
import com.barclays.paymentsystem.entity.User;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.repository.BillRepository;
import com.barclays.paymentsystem.repository.UserRepository;

import freemarker.template.Configuration;
import freemarker.template.Template;

@Service
@Transactional
public class BillServiceImp implements BillService {

	@Autowired
	BillRepository billRepository;
	
	@Autowired
	UserRepository userRepository;

    @Autowired
    private Configuration config;

    @Autowired
    private JavaMailSender mailSender;

	@Override
	public List<BillDTO> findAll(String username) throws PaymentSystemException {
		Optional<User> opt = userRepository.findById(username);

		if (!opt.isPresent())
			throw new PaymentSystemException(SystemConstants.USER_NOT_FOUND_RESPONSE);

		User user = opt.get();
		Account account = user.getAccount();

		List<Bills> list = billRepository.findByAccount(account);
		List<BillDTO> transactionList = new ArrayList<>();
		list.forEach(transaction -> transactionList.add(new BillDTO(transaction)));

		return transactionList;
	}

	@Override
	public List<BillDTO> findAllBetweenDate(String username, LocalDate from, LocalDate to)
			throws PaymentSystemException {
		Optional<User> opt = userRepository.findById(username);

		if (!opt.isPresent())
			throw new PaymentSystemException(SystemConstants.USER_NOT_FOUND_RESPONSE);

		User user = opt.get();
		Account account = user.getAccount();

		List<Bills> list = billRepository.findByAccountAndDueDateBetween(account, from, to);
		List<BillDTO> transactionList = new ArrayList<>();

		list.forEach(transaction -> transactionList.add(new BillDTO(transaction)));

		return transactionList;
	}
	
	@Override
    public String addNewBill(BillDTO billDTO) throws PaymentSystemException {
        Bills bills = billDTO.toEntity();
        Bills newBill= billRepository.save(bills);
        Map<String, Object> model = new HashMap<>();
        model.put("BillCode", newBill.getBillerCode().getBillerCode());
        model.put("BillName", newBill.getBillerCode().getName());
        model.put("ConsumerNumber", newBill.getConsumerNumber());
        model.put("Amount", newBill.getAmount());
        model.put("DueDate", newBill.getDueDate());
        model.put("Status", newBill.getStatus());
        model.put("AccountNumber", newBill.getAccount().getAccountNo());
        model.put("Name", newBill.getAccount().getName());
        model.put("EmailID",newBill.getAccount().getEmailId());
        model.put("CreationDate", LocalDateTime.now().toString());

        sendEmail(model,"utils.ftl");
        return newBill.getConsumerNumber();
    }

    private void sendEmail(Map<String, Object> model,String path){
        MimeMessage message = mailSender.createMimeMessage();
        if(model.get("EmailID") !=null && (model.get("EmailID").toString()).length()>0){
            try{
                MimeMessageHelper helper = new MimeMessageHelper(message, MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                        StandardCharsets.UTF_8.name());
                Template t = config.getTemplate(path);
                String html = FreeMarkerTemplateUtils.processTemplateIntoString(t, model);

                helper.setFrom("aniruddha.babar273@gmail.com");
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
