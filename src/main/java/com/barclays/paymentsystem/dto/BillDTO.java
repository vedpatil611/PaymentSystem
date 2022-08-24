package com.barclays.paymentsystem.dto;

import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.BillStatus;
import com.barclays.paymentsystem.entity.Bills;
import com.barclays.paymentsystem.entity.MasterBiller;

import javax.persistence.*;
import java.time.LocalDate;

public class BillDTO {
    int sequenceId;
    MasterBiller billerCode;
    String consumerNumber;
    double amount;
    LocalDate dueDate;
    String status;

    Account account;

    public BillDTO(){

    }
    public BillDTO(MasterBiller billerCode, String consumerNumber, double amount, LocalDate dueDate,Account account) {
        this.billerCode = billerCode;
        this.consumerNumber = consumerNumber;
        this.amount = amount;
        this.dueDate = dueDate;
        this.account=account;
    }

    public Bills toEntity() {
        Bills bills = new Bills();
        bills.setBillerCode(billerCode);
        bills.setConsumerNumber(consumerNumber);
        bills.setAmount(amount);
        bills.setDueDate(dueDate);
        bills.setStatus(BillStatus.PENDING);
        bills.setAccount(account);
        return bills;
    }

    public int getSequenceId() {
        return sequenceId;
    }

    public void setSequenceId(int sequenceId) {
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
