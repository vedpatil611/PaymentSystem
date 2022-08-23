package com.barclays.paymentsystem.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.barclays.paymentsystem.dto.RegisteredBillerDTO;
import com.barclays.paymentsystem.entity.MasterBiller;
import com.barclays.paymentsystem.service.UserService;

@Controller
//@RequestMapping
public class ManagerController {
	
	@Autowired 
	private UserService userservice;

	
	@GetMapping("/users/subscriptions")
	public List<MasterBiller> getBillers() {
		//List<MasterBillerDTO> list = masterBillerService.getAllMasterBiller();
		//return new ResponseEntity<>(list, HttpStatus.OK);
		return userservice.getAllBillers();
		//return biller;
	}
	
	@PostMapping("/user/subscriptions")
	public ResponseEntity<String> SubscribeNewBiller(@RequestBody RegisteredBillerDTO biller)  throws PaymentSystemException{
		String newCode = userservice.subscribeNewBiller(biller);
		return new ResponseEntity<>(newCode, HttpStatus.OK);
	}
	
	
}
