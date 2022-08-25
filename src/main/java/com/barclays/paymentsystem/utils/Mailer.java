package com.barclays.paymentsystem.utils;

import java.nio.charset.StandardCharsets;
import java.util.Map;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

public class Mailer {

    @Autowired
    static private Configuration config;

    @Autowired
    static private JavaMailSender mailSender;
	
    /**
	 * @sendEmail
	 * @param model,path
	 */
    public static void sendEmail(Map<String, Object> model,String path){
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
