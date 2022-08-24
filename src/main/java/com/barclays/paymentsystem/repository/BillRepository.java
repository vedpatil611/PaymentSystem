package com.barclays.paymentsystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.BillStatus;
import com.barclays.paymentsystem.entity.Bills;

public interface BillRepository extends CrudRepository<Bills, Integer> {
	List<Bills> findBySequenceId(Integer sequenceId);

	List<Bills> findByStatus(BillStatus status);
	
	Bills findByAccountAndBillerCode_billerCode(Account account, String billerCode);
}
