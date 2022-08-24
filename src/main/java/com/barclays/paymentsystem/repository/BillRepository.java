package com.barclays.paymentsystem.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.BillStatus;
import com.barclays.paymentsystem.entity.Bill;

/**
 * BillRepository - Interface for Bill table in database
 * @author PB3C
 *
 */


public interface BillRepository extends CrudRepository<Bill, Integer> {
	List<Bill> findBySequenceId(Integer sequenceId);

	List<Bill> findByStatus(BillStatus status);
	
	Bill findByAccountAndMasterBiller_billerCodeAndStatus(Account account, String billerCode, BillStatus status);
	
	List<Bill> findByAccount(Account account);
	
	List<Bill> findByAccountAndDueDateBetween(Account account, LocalDate from, LocalDate to);
}
