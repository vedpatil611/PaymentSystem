package com.barclays.paymentsystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.barclays.paymentsystem.entity.BillStatus;
import com.barclays.paymentsystem.entity.Bills;

public interface BillRepository extends CrudRepository<Bills, String> {
	List<Bills> findBySequenceId(Integer sequenceId);

	List<Bills> findByStatus(BillStatus status);
}
