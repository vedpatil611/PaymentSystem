package com.barclays.paymentsystem.dto;

import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.Role;
import com.barclays.paymentsystem.entity.User;

public class UserDTO {
	String username;
	String password;
	Account account;
	Role role;
	
	public UserDTO() {
		
	}
	
	public UserDTO(User user) {
		super();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.account = user.getAccount();
		this.role = user.getRole();
	}

	public User toUserEntity() {
		User user = new User();
		
		user.setUsername(username);
		user.setAccount(account);
		user.setPassword(password);
		user.setRole(role);
		
		return user;
	}


	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Account getAccount() {
		return account;
	}
	public void setAccount(Account account) {
		this.account = account;
	}
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}

}
