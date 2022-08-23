package com.barclays.paymentsystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.barclays.paymentsystem.entity.RegisteredBiller;

@Repository
public interface RegisteredBillerRepository extends CrudRepository<RegisteredBiller,Integer> {

}
