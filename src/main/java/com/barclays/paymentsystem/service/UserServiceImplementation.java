package com.barclays.paymentsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barclays.paymentsystem.entity.Auth;
import com.barclays.paymentsystem.entity.User;
import com.barclays.paymentsystem.repository.UserRepository;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class UserServiceImplementation implements UserService{
	
	@Autowired
	UserRepository userRepository;

	
	@Override
	public User createUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user);
	}

	
	

	@Override
	public String login(Auth authenticationDetails,HttpSession session) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(authenticationDetails.getUsername());
		
		if(user != null) {
			if(user.getPassword().equals(authenticationDetails.getPassword())) {
				session.setAttribute("user_id", user.getUsername());
				session.setAttribute("user_role", user.getRole());
				return "user logged in and Username is "+user.getUsername()+"with role"+user.getRole();
			}	
			else
				return "password did not match";
		}
		return "no such user found";
	}




	@Override
	public Optional<User> getUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}




	@Override
	public List<User> getAllUser() {
		// TODO Auto-generated method stub
		return null;
	}




	@Override
	public void deleteUser(Integer userId) {
		// TODO Auto-generated method stub
		
	}




	





}
