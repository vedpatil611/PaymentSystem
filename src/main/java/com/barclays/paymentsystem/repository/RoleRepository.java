package com.barclays.paymentsystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.barclays.paymentsystem.entity.Role;

public interface RoleRepository extends CrudRepository<Role, Integer> {
	
}
