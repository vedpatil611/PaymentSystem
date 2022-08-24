package com.barclays.paymentsystem.api;

import com.barclays.paymentsystem.dto.BillDTO;
import com.barclays.paymentsystem.dto.MasterBillerDTO;
import com.barclays.paymentsystem.entity.Bills;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.service.BillService;
import com.barclays.paymentsystem.service.MasterBillerService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillsController {
	@Autowired
	BillService billService;

	@PostMapping("/bill/new")
	public ResponseEntity<String> addNewBill(@RequestBody BillDTO billDTO) throws PaymentSystemException {
		String newCode = billService.addNewBill(billDTO);
		return new ResponseEntity<>(newCode, HttpStatus.OK);
	}

	@GetMapping("/user/{username}/bills")
	public ResponseEntity<List<BillDTO>> getBills(@PathVariable String username, @RequestParam LocalDate from,
			@RequestParam LocalDate to) throws PaymentSystemException {

		if (from == null || to == null) {
			List<BillDTO> bills = billService.findAll(username);
			return new ResponseEntity<>(bills, HttpStatus.OK);
		} else {
			List<BillDTO> bills = billService.findAllBetweenDate(username, from, to);
			return new ResponseEntity<>(bills, HttpStatus.OK);
		}
	}
}
