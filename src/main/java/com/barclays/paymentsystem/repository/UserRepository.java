package com.barclays.paymentsystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.barclays.paymentsystem.entity.User;

public interface UserRepository extends CrudRepository<User, String> {

}
