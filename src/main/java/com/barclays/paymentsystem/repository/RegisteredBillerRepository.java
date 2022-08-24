package com.barclays.paymentsystem.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.MasterBiller;
import com.barclays.paymentsystem.entity.RegisteredBiller;

@Repository
public interface RegisteredBillerRepository extends CrudRepository<RegisteredBiller,Integer> {
	List<RegisteredBiller> findByAccount(Account account);
	RegisteredBiller findByConsumerNumberAndBillerCodeAndAccount(String consumerNumber, MasterBiller masterBiller, Account account);
}
