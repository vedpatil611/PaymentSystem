package com.barclays.paymentsystem.dto;

import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.MasterBiller;
import com.barclays.paymentsystem.entity.RegisteredBiller;

/**
 * RegisteredBillerDTO - RegisteredBiller Data Transfer Object
 * 
 * @author PB3C
 *
 */
public class RegisteredBillerDTO {
	Integer sequenceID;
	MasterBiller billerCode;
	String consumerNumber;
	Account account;
	Boolean autopay;
	Double amount;
	Double autopayLimit;

	/**
	 * Create new RegisteredBillerDTO instance
	 */
	public RegisteredBillerDTO() {

	}

	/**
	 * Create new RegisteredBillerDTO instance from RegisteredBiller entity
	 * 
	 * @param registeredrBiller - RegisteredBiller entity
	 */
	public RegisteredBillerDTO(RegisteredBiller registeredBiller) {
		super();
		// this.sequenceID = registeredBiller.getSequenceID();
		this.billerCode = registeredBiller.getBillerCode();
		this.consumerNumber = registeredBiller.getConsumerNumber();
		this.account = registeredBiller.getAccount();
		this.autopay = registeredBiller.getAutopay();
		this.autopayLimit = registeredBiller.getAutopayLimit();
		this.amount = registeredBiller.getAmount();
	}

	/**
	 * Convert RegisteredBillerDTO to RegisteredBiller entity
	 * 
	 * @return RegisteredBiller entity
	 */
	public RegisteredBiller toRegisteredBillerEntity() {
		RegisteredBiller registeredBiller = new RegisteredBiller();
		registeredBiller.setBillerCode(billerCode);
		registeredBiller.setConsumerNumber(consumerNumber);
		registeredBiller.setAccount(account);
		registeredBiller.setAutopay(autopay);
		registeredBiller.setAutopayLimit(autopayLimit);
		registeredBiller.setAmount(amount);
		// registeredBiller.setSequenceID(sequenceID);
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setSequenceID(Integer sequenceID) {
		this.sequenceID = sequenceID;
	}

}
