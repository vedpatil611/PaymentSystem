package com.barclays.paymentsystem.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Bills {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	Integer sequenceId;

	@ManyToOne
	@JoinColumn(name = "biller_code",unique = false)
	MasterBiller billerCode;

	String consumerNumber;

	Double amount;

	LocalDate dueDate;

	@Enumerated(value = EnumType.STRING)
	BillStatus status = BillStatus.PENDING;

	@ManyToOne
	@JoinColumn(name = "account_no", unique = false)
	Account account;

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Integer getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
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

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public BillStatus getStatus() {
		return status;
	}

	public void setStatus(BillStatus status) {
		this.status = status;
	}

}
