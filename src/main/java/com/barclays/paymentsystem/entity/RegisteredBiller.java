package com.barclays.paymentsystem.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RegisteredBiller {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer sequenceID;
	@ManyToOne
	@JoinColumn(name = "biller_code", unique = true)
	MasterBiller billerCode;
	String consumerNumber;
	@ManyToOne
	@JoinColumn(name = "account_number")
	Account account;
	Boolean autopay;
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

}
