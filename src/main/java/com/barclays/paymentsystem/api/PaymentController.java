package com.barclays.paymentsystem.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.paymentsystem.constants.PaymentSystemControllerConstants;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.service.PaymentService;

/*
 * PaymentlController - Rest api for payment
   @author - P3BC
 */
@RestController
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	/**
	 * manuallyPayBill - Start new Payment
	 * @param username - Username of user initiated payment
	   @param billerCode - biller code of biller to pay bill
	 * @return Manual bill pay
	 * @throws PaymentSystemException
	 */
	@PostMapping(PaymentSystemControllerConstants.PAY_BILL_BILLERCODE)
	public ResponseEntity<String> manuallyPayBill(@PathVariable String username, @PathVariable String billerCode) throws PaymentSystemException {
		return new ResponseEntity<>(paymentService.manuallyPayBill(username, billerCode), HttpStatus.OK);
	}
}
