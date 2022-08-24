package com.barclays.paymentsystem.service;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;


import com.barclays.paymentsystem.entity.Auth;
import com.barclays.paymentsystem.entity.User;



public interface UserService {
	public User createUser(User user);

	public Optional<User> getUser(int userId);

	public void updateUser(User user);

	public List<User> getAllUser();

	public void deleteUser(Integer userId);

	public String login(Auth authenticationDetails, HttpSession session);

	

	
	
}
