package com.barclays.paymentsystem.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.service.AutoPayBillService;

@RestController
public class CronController {
	
	@Autowired
	AutoPayBillService autoPayBillService;
	
	@PostMapping("/cron/payall")
	public ResponseEntity<String> payAllBills() throws PaymentSystemException {
		return new ResponseEntity<>(autoPayBillService.autoPayBills(), HttpStatus.OK);
	}
}
