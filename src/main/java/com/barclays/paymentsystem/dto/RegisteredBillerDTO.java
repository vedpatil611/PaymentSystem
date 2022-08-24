package com.barclays.paymentsystem.dto;

import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.MasterBiller;
import com.barclays.paymentsystem.entity.RegisteredBiller;

public class RegisteredBillerDTO {
	Integer sequenceID;
	MasterBiller billerCode;
	String consumerNumber;
	Account account;
	Boolean autopay;
	Double autopayLimit;
	
	public RegisteredBillerDTO() {
		
	}
	
	public RegisteredBillerDTO(RegisteredBiller registeredBiller) {
		super();
		//this.sequenceID = registeredBiller.getSequenceID();
		this.billerCode = registeredBiller.getBillerCode();
		this.consumerNumber = registeredBiller.getConsumerNumber();
		this.account = registeredBiller.getAccount();
		this.autopay = registeredBiller.getAutopay();
		this.autopayLimit = registeredBiller.getAutopayLimit();
	}
	
	public RegisteredBiller toRegisteredBillerEntity() {
		RegisteredBiller registeredBiller = new RegisteredBiller();
		registeredBiller.setBillerCode(billerCode);
		registeredBiller.setConsumerNumber(consumerNumber);
		registeredBiller.setAccount(account);
		registeredBiller.setAutopay(autopay);
		registeredBiller.setAutopayLimit(autopayLimit);
	//	registeredBiller.setSequenceID(sequenceID);
		return registeredBiller;
	}
	
	public Integer getSequenceID() {
		return sequenceID;
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
