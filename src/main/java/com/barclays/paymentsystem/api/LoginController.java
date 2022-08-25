package com.barclays.paymentsystem.api;


import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.paymentsystem.dto.UserDTO;
import com.barclays.paymentsystem.entity.Auth;
import com.barclays.paymentsystem.entity.User;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.service.UserService;

@RestController
public class LoginController {
	
	@Autowired	
	UserService userService;
	
	@GetMapping(value="/")
	public ResponseEntity<?> homePage() throws PaymentSystemException {
		return new ResponseEntity<>("Welcome to the Payment System", HttpStatus.CREATED);	
	}
	
	@PostMapping(value="/user/create")
	public ResponseEntity<?> createUser(@RequestBody UserDTO user) throws PaymentSystemException {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("user_obj", userService.createUser(user));
		return new ResponseEntity<>(body, HttpStatus.CREATED);	
	}
	
	@GetMapping(value = "api/getuser/{username}")
	public ResponseEntity<?> getUser(@Valid @PathVariable String username) throws PaymentSystemException {
		Map<String, Object> body = new LinkedHashMap<>();
		body.put("user_obj",userService.getUser(username));
		return new ResponseEntity<>(body, HttpStatus.OK);
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> loginUser(@RequestBody Auth authenticationDetails, HttpSession session) throws PaymentSystemException{
		return new ResponseEntity<>(userService.login(authenticationDetails, session), HttpStatus.OK);
	}
	
	@GetMapping(value = "/login")
	public ResponseEntity<?> loginUser() throws PaymentSystemException {
		return new ResponseEntity<>("Welcome to the Payment System login", HttpStatus.OK);
		
	}
	
	@PostMapping("/logout")
	public ResponseEntity<?>  endSession(HttpServletRequest request) throws PaymentSystemException {
		request.getSession().invalidate();
		return new ResponseEntity<>("you are logged out", HttpStatus.OK);
	}
	
	
	@PutMapping(value="/users/{userId}")
	public ResponseEntity<?> updateUserDetails(@Valid @RequestBody User user) throws PaymentSystemException {
		userService.updateUser(user);
		return new ResponseEntity<>("Customer details have been successfully updated", HttpStatus.OK);
		
	}
	

	@GetMapping(value = "/allUsers")

	public ResponseEntity<?>  getAllUsers() throws PaymentSystemException {
		return new ResponseEntity<>(userService.getAllUser(), HttpStatus.OK);

	}



}
