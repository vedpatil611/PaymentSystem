package com.barclays.paymentsystem.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.paymentsystem.dto.AccountTransactionDTO;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.service.ManagerControllerService;

@RestController
public class ManagerController {

	@Autowired(required = false)
	ManagerControllerService managerControllerService;

	// get all account details
	@GetMapping("/{username}/accountTransaction")
	public ResponseEntity<List<AccountTransactionDTO>> getAllAccountTransaction(@PathVariable String username) throws PaymentSystemException {
		return new ResponseEntity<>(managerControllerService.findAll(username), HttpStatus.OK);
	}

//		@GetMapping("/AccountTransaction")
//		public List<AccountTransaction> getAllAccountTransaction(){
//			return managercontrollerRepository.findByColumnDateBetween();
//		}

//		@GetMapping("/AccountTransactionBetween")
//		public ResponseEntity<List<AccountTransaction>> getAllAccountTransactionByStartDate (@RequestParam Date startDate,
//																	@RequestParam Date endDate) {
//		LocalDateTime LocalDateTime = null;
//		return new ResponseEntity<List<AccountTransaction>>(managerControllerService.findByDateTimeBetween(LocalDateTime, LocalDateTime), HttpStatus.OK);
//		}

}
