package com.barclays.paymentsystem.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.barclays.paymentsystem.entity.MasterBiller;

@Repository
public interface MasterBillerRepository extends CrudRepository<MasterBiller,Integer>{

}
