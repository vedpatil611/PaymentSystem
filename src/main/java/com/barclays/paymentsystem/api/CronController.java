package com.barclays.paymentsystem.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.paymentsystem.constants.PaymentSystemControllerConstants;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.service.BillService;

/*
 * CronController - Rest api for Auto Pay bills
   @author PB3C
 */
@RestController
public class CronController {

	@Autowired
	BillService billService;

	/**
	 * payAllBills - Auto Pay Bills
	 * 
	 * @param
	 * @return Auto pay bills
	 * @throws PaymentSystemException
	 */
	@PostMapping(PaymentSystemControllerConstants.AUTOPAY_BILLS)
	public ResponseEntity<String> payAllBills() throws PaymentSystemException {
		return new ResponseEntity<>(billService.autoPayBills(), HttpStatus.OK);
	}
}
