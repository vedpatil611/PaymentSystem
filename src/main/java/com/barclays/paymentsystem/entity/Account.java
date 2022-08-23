package com.barclays.paymentsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Account {

	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer sequenceId;
	@Id
	Integer accountNo;
	String name;
	String emailId;
	Double currentBalance;

	public Integer getSequenceId() {
		return sequenceId;
	}
	
	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
	}
	
	public Integer getAccountNo() {
		return accountNo;
	}
	
	public void setAccountNo(Integer accountNo) {
		this.accountNo = accountNo;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public Double getCurrentBalance() {
		return currentBalance;
	}
	
	public void setCurrentBalance(Double currentBalance) {
		this.currentBalance = currentBalance;
	}
}
