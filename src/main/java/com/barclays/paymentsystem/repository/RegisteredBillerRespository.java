package com.barclays.paymentsystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.MasterBiller;
import com.barclays.paymentsystem.entity.RegisteredBiller;

public interface RegisteredBillerRespository extends CrudRepository<RegisteredBiller, Integer> {
	RegisteredBiller findByConsumerNumberAndBillerCodeAndAccount(String consumerNumber, MasterBiller masterBiller, Account account);
}
