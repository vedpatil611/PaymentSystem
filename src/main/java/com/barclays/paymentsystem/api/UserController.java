package com.barclays.paymentsystem.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.paymentsystem.constants.PaymentSystemControllerConstants;
import com.barclays.paymentsystem.dto.RegisteredBillerDTO;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.service.UserService;

/**
 * UserController - Rest api for User details
 */
@RestController
public class UserController {

	@Autowired
	private UserService userservice;

	/**
	 * getBillers - Get list of subscribed billers by user
	 * 
	 * @param username
	 * @return List of subscribed billers
	 * @throws PaymentSystemException
	 */
	@GetMapping(PaymentSystemControllerConstants.GET_SUBSCRIBED_BILLERS)
	public ResponseEntity<List<RegisteredBillerDTO>> getBillers(@PathVariable(name = "username") String username) throws PaymentSystemException{
		//List<MasterBillerDTO> list = masterBillerService.getAllMasterBiller();
		//return new ResponseEntity<>(list, HttpStatus.OK);
		return new ResponseEntity<>(userservice.getAllSubscribedBillers(username), HttpStatus.OK);
		// return biller;
	}

	/**
	 * subscribeNewBiller - Subscribe to new biller
	 * 
	 * @param username
	 * @return New Biller Code
	 * @throws PaymentSystemException
	 */
	@PostMapping(PaymentSystemControllerConstants.SUBSCRIBE_NEW_BILLER)
	public ResponseEntity<String> subscribeNewBiller(@PathVariable String username, @RequestBody RegisteredBillerDTO biller) throws PaymentSystemException{
		String newCode = userservice.subscribeNewBiller(username, biller);
		return new ResponseEntity<>(newCode, HttpStatus.OK);
	}

}
