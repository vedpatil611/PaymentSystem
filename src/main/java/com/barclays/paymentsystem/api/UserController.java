package com.barclays.paymentsystem.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.barclays.paymentsystem.dto.RegisteredBillerDTO;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.service.UserService;

@Controller
//@RequestMapping
public class UserController {
	
	@Autowired 
	private UserService userservice;

	
	@GetMapping("/users/{uid}/allsubscriptions")
	public List<RegisteredBillerDTO> getBillers(@PathVariable(name = "uid") String uid) throws PaymentSystemException{
		//List<MasterBillerDTO> list = masterBillerService.getAllMasterBiller();
		//return new ResponseEntity<>(list, HttpStatus.OK);
		return userservice.getAllSubscribedBillers(uid);
		//return biller;
	}
	
	@PostMapping("/users/subscribe")
	public ResponseEntity<String> subscribeNewBiller(@RequestBody RegisteredBillerDTO biller)  throws PaymentSystemException{
		String newCode = userservice.subscribeNewBiller(biller);
		return new ResponseEntity<>(newCode, HttpStatus.OK);
	}
	
	
}
