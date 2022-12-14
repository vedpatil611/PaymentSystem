package com.barclays.paymentsystem.service;

import java.util.List;

import com.barclays.paymentsystem.dto.RegisteredBillerDTO;
import com.barclays.paymentsystem.exception.PaymentSystemException;

/**
 * UserService - User service interface
 * @author PB3C
 *
 */

public interface UserService {

	public String subscribeNewBiller(String username, RegisteredBillerDTO registeeredBillerDTO) throws PaymentSystemException;

	List<RegisteredBillerDTO> getAllSubscribedBillers(String userId) throws PaymentSystemException;

}
