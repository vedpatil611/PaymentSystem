package com.barclays.paymentsystem.api;

import java.time.LocalDate;
import java.util.List;

import com.barclays.paymentsystem.dto.AccountDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.paymentsystem.constants.PaymentSystemControllerConstants;
import com.barclays.paymentsystem.dto.BillDTO;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.service.BillService;

/*
 * BillController - Rest api for Bill details
 */
@RestController
public class BillsController {

	@Autowired
	BillService billService;
	
	/**
	 * addNewBill - Create new Biller
	 * @param Biller - Biller data
	 * @return New Biller Code
	 * @throws PaymentSystemException
	 */
	@PostMapping(PaymentSystemControllerConstants.NEW_BILL)
	public ResponseEntity<String> addNewBill(@RequestBody BillDTO billDTO) throws PaymentSystemException {
		String newCode = billService.addNewBill(billDTO);
		return new ResponseEntity<>(newCode, HttpStatus.OK);
	}

	@GetMapping("/bill/list")
	public ResponseEntity<List<BillDTO>> getAllBills() throws PaymentSystemException {
		List<BillDTO> list = billService.getAllBills();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	/**
	 * getBills - Get Bills
	 * @param username - username of user
	 * @return bills
	 * @throws PaymentSystemException
	 */
	@GetMapping(PaymentSystemControllerConstants.GET_BILL_USERNAME)
	public ResponseEntity<List<BillDTO>> getBills(@PathVariable String username,
			@RequestParam(required = false) LocalDate from, 
			@RequestParam(required = false) LocalDate to)
			throws PaymentSystemException {

		if (from == null || to == null) {
			List<BillDTO> bills = billService.findAll(username);
			return new ResponseEntity<>(bills, HttpStatus.OK);
		} else {
			List<BillDTO> bills = billService.findAllBetweenDate(username, from, to);
			return new ResponseEntity<>(bills, HttpStatus.OK);
		}
	}
}
