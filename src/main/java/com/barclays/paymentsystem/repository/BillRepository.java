package com.barclays.paymentsystem.repository;

import com.barclays.paymentsystem.entity.BillStatus;
import com.barclays.paymentsystem.entity.Bills;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bills, String> {
	List<Bills> findByStatus(BillStatus status);
}
