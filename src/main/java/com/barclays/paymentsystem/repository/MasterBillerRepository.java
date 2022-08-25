package com.barclays.paymentsystem.repository;

import org.springframework.data.repository.CrudRepository;

import org.springframework.stereotype.Repository;

import com.barclays.paymentsystem.entity.MasterBiller;

/**
 * MasterBillerRepository - Interface for MasterBiller table in database
 * @author PB3C
 *
 */


@Repository
public interface MasterBillerRepository extends CrudRepository<MasterBiller, String> {

}
