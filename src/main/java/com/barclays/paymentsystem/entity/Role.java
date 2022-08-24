package com.barclays.paymentsystem.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/**
 * Defining RoleName as Manager or Account
 * @author PB3C
 *
 */
enum RoleName {
	MANAGER,
	ACCOUNT
}
/**
 * Role - Role Entity mapped to Role table in database
 * @author PB3C
 *
 */
@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer rollId;
	@Enumerated(value = EnumType.STRING)
	RoleName name;
	
	public Integer getRollId() {
		return rollId;
	}
	
	public void setRollId(Integer rollId) {
		this.rollId = rollId;
	}
	
	public RoleName getName() {
		return name;
	}
	
	public void setName(RoleName name) {
		this.name = name;
	}
}
