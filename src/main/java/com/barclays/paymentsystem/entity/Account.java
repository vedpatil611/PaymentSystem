package com.barclays.paymentsystem.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Account - Account Entity mapped to Account table in database
 * @author PB3C
 *
 */
@Entity
public class Account {
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(unique = true, nullable = false)
	Integer sequenceId;
	@Id
	String accountNo;
	String name;
	String emailId;
	Double currentBalance;

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

	@Override
	public int hashCode() {
		return Objects.hash(accountNo, currentBalance, emailId, name, sequenceId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(accountNo, other.accountNo) && Objects.equals(currentBalance, other.currentBalance)
				&& Objects.equals(emailId, other.emailId) && Objects.equals(name, other.name)
				&& Objects.equals(sequenceId, other.sequenceId);
	}
}
