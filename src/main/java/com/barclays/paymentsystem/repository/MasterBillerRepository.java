package com.barclays.paymentsystem.repository;

import org.springframework.data.repository.CrudRepository;

import com.barclays.paymentsystem.entity.MasterBiller;

public interface MasterBillerRepository extends CrudRepository<MasterBiller, String> {

}
