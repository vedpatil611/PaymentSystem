package com.barclays.paymentsystem.api;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.paymentsystem.dto.AccountTransactionDTO;
import com.barclays.paymentsystem.entity.AccountTransaction;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.service.ManagerControllerService;

@RestController
public class ManagerController {

	@Autowired(required = false)
	ManagerControllerService managerControllerService;

	// get all account details
	@GetMapping("/{username}/accountTransaction")
	public ResponseEntity<List<AccountTransactionDTO>> getAllAccountTransaction(@PathVariable String username)
			throws PaymentSystemException {
		return new ResponseEntity<>(managerControllerService.findAll(username), HttpStatus.OK);
	}

	@GetMapping("/{username}/accountTransactionBetween")
	public ResponseEntity<List<AccountTransactionDTO>> getAllAccountTransactionByStartDate(
			@PathVariable String username, @RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate)
			throws PaymentSystemException {
		
		return new ResponseEntity<>(managerControllerService.findAllBetweenDate(username, startDate, endDate),
				HttpStatus.OK);
	}

}
