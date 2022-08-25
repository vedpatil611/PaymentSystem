package com.barclays.paymentsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;


/**
 * User - User Entity mapped to User table in database
 * 
 * @author PB3C
 *
 */
@Entity
public class User {
	@Id
	String username;
	String password;
	@OneToOne
	@JoinColumn(name = "account_no", unique = true)
	Account account;
	@ManyToOne
	@JoinColumn(name = "role_id", unique = false)
	Role role;

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

	public void setRole(Role role) {
		this.role = role;
	}
	
	public Role getRole() {
		return role;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
	
	public User(String username ,String password,Role role,Account account) {
		this.password = password;
		this.username = username;
		this.account=account;
		this.role = role;
	}

	public User() {
	}
}
