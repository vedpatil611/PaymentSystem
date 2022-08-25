package com.barclays.paymentsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * RegisteredBiller - RegisteredBiller Entity mapped to RegisteredBiller table
 * in database
 * 
 * @author PB3C
 *
 */
@Entity
public class RegisteredBiller {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer sequenceID;
	@ManyToOne
	@JoinColumn(name = "biller_code", unique = false)
	MasterBiller billerCode;

	String consumerNumber;
	@ManyToOne
	@JoinColumn(name = "account_no")
	Account account;
	Boolean autopay;
	Double amount;
	Double autopayLimit;

	public Integer getSequenceID() {
		return sequenceID;
	}

	public void setSequenceID(Integer sequenceID) {
		this.sequenceID = sequenceID;
	}

	public MasterBiller getBillerCode() {
		return billerCode;
	}

	public void setBillerCode(MasterBiller billerCode) {
		this.billerCode = billerCode;
	}

	public String getConsumerNumber() {
		return consumerNumber;
	}

	public void setConsumerNumber(String consumerNumber) {
		this.consumerNumber = consumerNumber;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Boolean getAutopay() {
		return autopay;
	}

	public void setAutopay(Boolean autopay) {
		this.autopay = autopay;
	}

	public Double getAutopayLimit() {
		return autopayLimit;
	}

	public void setAutopayLimit(Double autopayLimit) {
		this.autopayLimit = autopayLimit;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

}
