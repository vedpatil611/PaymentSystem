package com.barclays.paymentsystem.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Role - Role Entity mapped to Role table in database
 * 
 * @author PB3C
 *
 */
@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer roleId;
	@Enumerated(value = EnumType.STRING)
	RoleName name;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer rollId) {
		this.roleId = rollId;
	}

	public RoleName getName() {
		return name;
	}

	public void setName(RoleName name) {
		this.name = name;
	}
}
