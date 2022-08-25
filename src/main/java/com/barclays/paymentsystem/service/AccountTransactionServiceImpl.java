package com.barclays.paymentsystem.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barclays.paymentsystem.constants.SystemConstants;
import com.barclays.paymentsystem.dto.AccountTransactionDTO;
import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.AccountTransaction;
import com.barclays.paymentsystem.entity.User;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.repository.AccountTransactionRepository;
import com.barclays.paymentsystem.repository.UserRepository;

/**
 * AccountTransactionServiceImpl - Account Transaction service interface implementation
 * @author PB3C
 *
 */

@Service
@Transactional
public class AccountTransactionServiceImpl implements AccountTransactionService {

	@Autowired
	private AccountTransactionRepository managercontrollerRepository;

	@Autowired
	UserRepository userRepository;
	
	/**
	 * To find Transaction Details
	 * @param username
	 * @return List of Transaction Details
	 * @throws PaymentSystemException
	 */

	@Override
	public List<AccountTransactionDTO> findAll(String username) throws PaymentSystemException {
		Optional<User> opt = userRepository.findById(username);

		if (!opt.isPresent())
			throw new PaymentSystemException(SystemConstants.USER_NOT_FOUND_RESPONSE);

		User user = opt.get();
		Account account = user.getAccount();

		List<AccountTransaction> list = managercontrollerRepository.findAllByRefNo_account(account);
		List<AccountTransactionDTO> transactionList = new ArrayList<>();

		list.forEach(transaction -> transactionList.add(new AccountTransactionDTO(transaction)));

		return transactionList;
	}
	
	/**
	 * To find Transaction Details Between Specified Date range
	 * @param usernam
	 * @param from
	 * @param to
	 * @return List of Transaction Details
	 * @throws PaymentSystemException
	 */
	
	@Override
	public List<AccountTransactionDTO> findAllBetweenDate(String username, LocalDateTime from, LocalDateTime to)
			throws PaymentSystemException {
		Optional<User> opt = userRepository.findById(username);

		if (!opt.isPresent())
			throw new PaymentSystemException(SystemConstants.USER_NOT_FOUND_RESPONSE);

		User user = opt.get();
		Account account = user.getAccount();

		List<AccountTransaction> list = managercontrollerRepository.findAllByRefNo_accountAndDateTimeBetween(account, from, to);
		List<AccountTransactionDTO> transactionList = new ArrayList<>();

		list.forEach(transaction -> transactionList.add(new AccountTransactionDTO(transaction)));

		return transactionList;
	}

}
