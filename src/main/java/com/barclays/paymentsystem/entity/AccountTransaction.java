package com.barclays.paymentsystem.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class AccountTransaction {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer sequenceId;
	Integer transactionReference;
	LocalDateTime dateTime;
	Double amount;
	TransactionType type;
	String description;
	@OneToOne
	@JoinColumn(name = "sequence_id", unique = true)
	Bill refNo;

	public Integer getSequenceId() {
		return sequenceId;
	}

	public void setSequenceId(Integer sequenceId) {
		this.sequenceId = sequenceId;
	}

	public Integer getTransactionReference() {
		return transactionReference;
	}

	public void setTransactionReference(Integer transactionReference) {
		this.transactionReference = transactionReference;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Bill getRefNo() {
		return refNo;
	}

	public void setRefNo(Bill refNo) {
		this.refNo = refNo;
	}
}
