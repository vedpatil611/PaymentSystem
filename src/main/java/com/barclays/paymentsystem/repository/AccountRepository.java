package com.barclays.paymentsystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.barclays.paymentsystem.entity.Account;

public interface AccountRepository extends CrudRepository<Account, Integer> {

}
