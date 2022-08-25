package com.barclays.paymentsystem.service;

import java.time.LocalDateTime;

/**
 * AccounntTransactionService - Interface for Account Transaction service api
 * @author PB3C
 *
 */

import java.util.List;

import com.barclays.paymentsystem.dto.AccountTransactionDTO;
import com.barclays.paymentsystem.exception.PaymentSystemException;

public interface AccountTransactionService {

	List<AccountTransactionDTO> findAll(String username) throws PaymentSystemException;

	List<AccountTransactionDTO> findAllBetweenDate(String username, LocalDateTime from, LocalDateTime to) throws PaymentSystemException;

}