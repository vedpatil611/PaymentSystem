package com.barclays.paymentsystem.repository;

import com.barclays.paymentsystem.entity.Bills;
import com.barclays.paymentsystem.entity.MasterBiller;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bills, String> {

}