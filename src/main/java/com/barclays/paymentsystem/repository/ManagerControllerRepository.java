package com.barclays.paymentsystem.repository;

import java.util.List;
import java.time.LocalDateTime;
import org.springframework.data.repository.CrudRepository;

import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.AccountTransaction;

public interface ManagerControllerRepository extends CrudRepository<AccountTransaction, Integer> {

	List<AccountTransaction> findAllByRefNo_account(Account account);

	List<AccountTransaction> findAllByRefNo_accountAndDateTimeBetween(Account account, LocalDateTime from, LocalDateTime to);

}
