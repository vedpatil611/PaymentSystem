package com.barclays.paymentsystem.dto;

<<<<<<< HEAD
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.MasterBiller;

public class AccountDTO {

	Integer sequenceId;
	Integer accountNo;
	String name;
	String emailId;
	Double currentBalance;
	
	public AccountDTO() {
	}
	
	public AccountDTO(Account Account) {
		this.sequenceId=Account.getSequenceId();
		this.accountNo=Account.getAccountNo();
		this.name=Account.getName();
		this.emailId=Account.getEmailId();
		this.currentBalance=Account.getCurrentBalance();
=======
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
>>>>>>> 58607f1eccdc5f8eb90676d49369219203670b6a
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
<<<<<<< HEAD
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

	}
=======

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
>>>>>>> 58607f1eccdc5f8eb90676d49369219203670b6a
