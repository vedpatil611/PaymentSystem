package com.barclays.paymentsystem.service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barclays.paymentsystem.constants.SystemConstants;

import com.barclays.paymentsystem.dto.BillDTO;
import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.Bills;
import com.barclays.paymentsystem.entity.User;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.repository.ViewBillsRepository;

import com.barclays.paymentsystem.repository.UserRepository;

@Service
@Transactional
public class ViewBillsServiceImpl implements ViewBillsService {

    @Autowired
    private ViewBillsRepository ViewBillsRepository;

    @Autowired
    UserRepository userRepository;

    @Override
	public
     List <Bills> findAll() {
        return ViewBillsRepository.findAll();
    public List <BillDTO> findAll(String username) throws PaymentSystemException {
        Optional<User> opt = userRepository.findById(username);

        if (!opt.isPresent()) 
        	throw new PaymentSystemException(SystemConstants.USER_NOT_FOUND_RESPONSE);

        User user = opt.get();
        Account account = user.getAccount();

        List<Bills> list = ViewBillsRepository.findAllBysequenceId(Integer sequenceId);
        List<BillDTO> transactionList = new ArrayList<>();

        list.forEach(transaction -> transactionList.add(new BillsDTO(transaction)));

        return transactionList;
    }

	@Override
	public List<BillDTO> findAllBetweenDate(String username, LocalDateTime from, LocalDateTime to) throws PaymentSystemException {
Optional<User> opt = userRepository.findById(username);

        if (!opt.isPresent()) 
        	throw new PaymentSystemException(SystemConstants.USER_NOT_FOUND_RESPONSE);

        User user = opt.get();
        Account account = user.getAccount();

        List<Bills> list = ViewBillsRepository.findAllByRefNo_accountAndDateTimeBetween(account, from, to);
        List<BillsDTO> transactionList = new ArrayList<>();

        list.forEach(transaction -> transactionList.add(new BillsDTO(transaction)));

        return transactionList;
	}


}