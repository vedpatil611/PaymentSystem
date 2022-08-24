package com.barclays.paymentsystem.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.service.PaymentService;

@RestController
public class PaymentController {
	
	@Autowired
	PaymentService paymentService;
	
	@PostMapping("/user/{username}/paybill/{billerCode}")
	public ResponseEntity<String> manuallyPayBill(@PathVariable String username, @PathVariable String billerCode) throws PaymentSystemException {
		return new ResponseEntity<>(paymentService.manuallyPayBill(username, billerCode), HttpStatus.OK);
	}
}
