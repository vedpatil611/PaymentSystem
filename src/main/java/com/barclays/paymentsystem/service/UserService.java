package com.barclays.paymentsystem.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import com.barclays.paymentsystem.dto.RegisteredBillerDTO;
import com.barclays.paymentsystem.entity.Auth;
import com.barclays.paymentsystem.entity.User;
import com.barclays.paymentsystem.exception.PaymentSystemException;



/**
 * UserService - User service interface
 * @author PB3C
 *
 */

public interface UserService {
	public User createUser(User user);

	public User getUser(String username);

	public void updateUser(User user);

	public List<User> getAllUser();

	public void deleteUser(String username);

	public String login(Auth authenticationDetails, HttpSession session);

	/**
	 * getAllSubscribedBillers
	 * @param userId
	 * @return List of subscribed billers
	 * @throws PaymentSystemException
	 */
	List<RegisteredBillerDTO> getAllSubscribedBillers(String userId) throws PaymentSystemException;

	/**
	 * subscribeNewBiller
	 * @param username
	 * @param registeredBillerDTO
	 * @return consumer number of new biller
	 * @throws PaymentSystemException
	 */
	String subscribeNewBiller(String username, RegisteredBillerDTO registeredBillerDTO) throws PaymentSystemException;
	
}
