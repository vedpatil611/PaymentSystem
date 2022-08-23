package com.barclays.paymentsystem.repository;
import java.util.List;

import com.barclays.paymentsystem.entity.BillStatus;
import com.barclays.paymentsystem.entity.Bills;
import com.barclays.paymentsystem.entity.MasterBiller;
import org.springframework.data.repository.CrudRepository;

public interface BillRepository extends CrudRepository<Bills, String> {
	List<Bills> findBySequenceId(Integer sequenceId );
	List<Bills> findByStatus(BillStatus status);
}


