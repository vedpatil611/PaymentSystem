package com.barclays.paymentsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class User {
	@Id
	private Integer id;
	String username;
	String password;
	@OneToOne
	@JoinColumn(name = "account_no", unique = true)
	Account account;
	
	private String role;

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

	public void setRole(String role) {
		this.role = role;
	}
	
	public String getRole() {
		return role;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	public User(String username ,String password,String role,Account account) {
		// TODO Auto-generated constructor stub
		this.password = password;
		this.username = username;
		this.account=account;
		this.role = role;
	}

	public User() {
		// TODO Auto-generated constructor stub
	}


	

}
