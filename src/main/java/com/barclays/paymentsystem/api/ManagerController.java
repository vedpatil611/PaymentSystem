package com.barclays.paymentsystem.api;

import java.util.List;
import java.util.Date;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;


import com.barclays.paymentsystem.entity.AccountTransaction;
import com.barclays.paymentsystem.repository.ManagerControllerRepository;
import com.barclays.paymentsystem.service.ManagerControllerService;




@RestController
		public class ManagerController {

		
	@Autowired(required=false)
	ManagerControllerService managerControllerService;
	
		//get all account details
		@GetMapping("/AccountTransaction")
		public List<AccountTransaction> getAllAccountTransaction(){
			return managerControllerService.findAll();
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
