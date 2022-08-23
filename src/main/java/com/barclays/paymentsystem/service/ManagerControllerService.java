package com.barclays.paymentsystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barclays.paymentsystem.entity.AccountTransaction;
import com.barclays.paymentsystem.exception.PaymentSystemException;



@Service
@Transactional

public interface ManagerControllerService {
	
	List<AccountTransaction> findAll();



}