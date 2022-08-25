package com.barclays.paymentsystem.dto;

import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.BillStatus;
import com.barclays.paymentsystem.entity.Bill;
import com.barclays.paymentsystem.entity.MasterBiller;

import java.time.LocalDate;

public class BillDTO {
    Integer sequenceId;
    MasterBiller billerCode;
    String consumerNumber;
    double amount;
    LocalDate dueDate;
    BillStatus status = BillStatus.PENDING;
    Account account;

    public BillDTO() {

    }
    
    public BillDTO(MasterBiller billerCode, String consumerNumber, double amount, LocalDate dueDate, BillStatus status) {
    	 this.billerCode = billerCode;
         this.consumerNumber = consumerNumber;
         this.amount = amount;
         this.dueDate = dueDate;
         this.status = status;
    }
    
    public BillDTO(MasterBiller billerCode, String consumerNumber, double amount, LocalDate dueDate,Account account) {
        this.billerCode = billerCode;
        this.consumerNumber = consumerNumber;
        this.amount = amount;
        this.dueDate = dueDate;
        this.account= account;
    }
    
    public BillDTO(Bill bill) {
    	this.sequenceId = bill.getSequenceId();
    	this.billerCode = bill.getMasterBiller();
    	this.consumerNumber = bill.getConsumerNumber();
    	this.amount = bill.getAmount();
    	this.dueDate = bill.getDueDate();
    	this.status = bill.getStatus();
    	this.account = bill.getAccount();
    }

    public Bill toEntity() {
        Bill bills = new Bill();
        bills.setSequenceId(sequenceId);
        bills.setMasterBiller(billerCode);
        bills.setConsumerNumber(consumerNumber);
        bills.setAmount(amount);
        bills.setDueDate(dueDate);
        bills.setStatus(status);
        bills.setAccount(account);
        return bills;
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}
}
