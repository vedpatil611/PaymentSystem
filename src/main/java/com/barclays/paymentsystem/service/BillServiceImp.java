package com.barclays.paymentsystem.service;

import com.barclays.paymentsystem.constants.SystemConstants;
import com.barclays.paymentsystem.dto.BillDTO;
import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.Bills;
import com.barclays.paymentsystem.entity.MasterBiller;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.repository.BillRepository;
import com.barclays.paymentsystem.repository.MasterBillerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import javax.mail.internet.MimeMessage;
import javax.xml.transform.Templates;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Objects;
import java.util.Optional;
import java.util.Map;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

@Service
@Transactional
public class BillServiceImp implements BillService{

    @Autowired
    BillRepository billRepository;

    @Autowired
    private Configuration config;

    @Autowired
    private JavaMailSender mailSender;



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
<<<<<<< HEAD
    
=======

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
>>>>>>> 58607f1eccdc5f8eb90676d49369219203670b6a
}

