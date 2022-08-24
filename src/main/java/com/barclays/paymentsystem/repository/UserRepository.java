package com.barclays.paymentsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.barclays.paymentsystem.entity.User;


public interface UserRepository extends JpaRepository<User, Integer>  {

	public User findByUsername(String username);

}
