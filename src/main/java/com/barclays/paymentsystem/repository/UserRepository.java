package com.barclays.paymentsystem.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.barclays.paymentsystem.entity.User;


@Repository
public interface UserRepository extends CrudRepository<User,String>{
	
}
