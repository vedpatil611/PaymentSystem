package com.barclays.paymentsystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.barclays.paymentsystem.entity.Account;

/**
 * AccountRepository - Interface for Account table in database
 * @author PB3C
 *
 */

public interface AccountRepository extends CrudRepository<Account, String> {


}
