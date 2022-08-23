package com.barclays.paymentsystem.repository;
import java.util.List;
import java.util.Date;
import java.time.LocalDateTime;
//import java.math.BigDecimal;

//import org.springframework.data.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.barclays.paymentsystem.entity.AccountTransaction;

@Repository

//public interface ManagerControllerRepository extends CrudRepository<AccountTransaction, Long> 
public interface ManagerControllerRepository extends JpaRepository<AccountTransaction, Long>{
//	List<AccountTransaction> findByDateTimeBetween(LocalDateTime to, LocalDateTime from);
		
	
	
}
