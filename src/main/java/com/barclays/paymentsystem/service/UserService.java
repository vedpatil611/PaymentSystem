package com.barclays.paymentsystem.service;

import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class UserService {

    @Transactional
    public void emailService(Integer sequence_id, Integer biller_code, String consumer_number,Integer amount, Date due, String status, String account_no) {


        if(email !=null && email.length()>0 && !Objects.equals(student.getEmail(),email)){

            Optional<Student> sOptional = studentRepository.findStudentByEmail(email);

            if(sOptional.isPresent()){
                throw new IllegalStateException("Email taken");

            }

            SimpleMailMessage mailMessage=new SimpleMailMessage();
            mailMessage.setFrom("aniruddha.babar273@gmail.com");
            mailMessage.setTo(email);
            mailMessage.setText("You have successfully registered for an event...");
            mailMessage.setSubject("Event Registration");
            mailSender.send(mailMessage);

            student.setEmail(email);
            System.out.print("Mail Send");
        }


    }

}
