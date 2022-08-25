package com.barclays.paymentsystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.barclays.paymentsystem.entity.User;

/**
 * UserRepository - Interface for User table in database
 * @author PB3C
 *
 */
public interface UserRepository extends CrudRepository<User, String> {

	public User findByUsername(String username);

}
