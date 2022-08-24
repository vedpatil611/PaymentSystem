package com.barclays.paymentsystem.service;

import java.time.LocalDateTime;
import java.util.List;

import com.barclays.paymentsystem.dto.AccountTransactionDTO;
import com.barclays.paymentsystem.exception.PaymentSystemException;

public interface ManagerControllerService {

	List<AccountTransactionDTO> findAll(String username) throws PaymentSystemException;

	List<AccountTransactionDTO> findAllBetweenDate(String username, LocalDateTime from, LocalDateTime to) throws PaymentSystemException;

}