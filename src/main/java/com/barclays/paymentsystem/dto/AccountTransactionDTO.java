package com.barclays.paymentsystem.dto;

import java.time.LocalDateTime;

import com.barclays.paymentsystem.entity.AccountTransaction;
import com.barclays.paymentsystem.entity.Bills;
import com.barclays.paymentsystem.entity.TransactionType;

/**
 * AccountTransactionDTO - AccountTransaction Data Transfer Object
 * @author Ved
 *
 */
public class AccountTransactionDTO {
	Integer sequenceId;
	Integer transactionReference;
	LocalDateTime dateTime;
	Double amount;
	TransactionType type;
	String description;
	Bills refNo;
	
	/**
	 * Create new instance of AccountTransactionDTO
	 */
	public AccountTransactionDTO() {

	}

	/**
	 * Create new instance of AccountTransactionDTO from AccountTransaction
	 * @param accountTransaction
	 */
	public AccountTransactionDTO(AccountTransaction accountTransaction) {
		this.sequenceId = accountTransaction.getSequenceId();
		this.transactionReference = accountTransaction.getTransactionReference();
		this.dateTime = accountTransaction.getDateTime();
		this.amount = accountTransaction.getAmount();
		this.type = accountTransaction.getType();
		this.description = accountTransaction.getDescription();
		this.refNo = accountTransaction.getRefNo();
	}
	
	/**
	 * Convert AccountTransactionDTO to AccountTransaction entity
	 * @return
	 */
	public AccountTransaction toEntity() {
		AccountTransaction accountTransaction = new AccountTransaction();
		accountTransaction.setSequenceId(sequenceId);
		accountTransaction.setTransactionReference(transactionReference);
		accountTransaction.setDateTime(dateTime);
		accountTransaction.setAmount(amount);
		accountTransaction.setType(type);
		accountTransaction.setDescription(description);
		accountTransaction.setRefNo(refNo);
		return accountTransaction;
	}
	
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

	public Bills getRefNo() {
		return refNo;
	}

	public void setRefNo(Bills refNo) {
		this.refNo = refNo;
	}
}
