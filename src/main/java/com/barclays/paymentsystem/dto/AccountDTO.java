package com.barclays.paymentsystem.dto;

import com.barclays.paymentsystem.entity.Account;

public class AccountDTO {
	Integer sequenceId;
	String accountNo;
	String name;
	String emailId;
	Double currentBalance;

	public AccountDTO(Account account) {
		sequenceId = account.getSequenceId();
		accountNo = account.getAccountNo();
		name = account.getName();
		emailId = account.getEmailId();
		currentBalance = account.getCurrentBalance();
	}
	
	public Account toEntity() {
		Account account = new Account();
		account.setSequenceId(sequenceId);
		account.setAccountNo(accountNo);
		account.setName(name);
		account.setEmailId(emailId);
		account.setCurrentBalance(currentBalance);
		return account;
	}
	
	public Integer getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
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
