package com.barclays.paymentsystem.dto;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.barclays.paymentsystem.entity.Role;
import com.barclays.paymentsystem.entity.RoleName;

public class RoleDTO {
	Integer roleId;
	@Enumerated(value = EnumType.STRING)
	RoleName name;

	public RoleDTO() {
		
	}
	
	public RoleDTO(Role role) {
		roleId = role.getRoleId();
		name = role.getName();
	}
	
	public Role toEntity() {
		Role role = new Role();
		role.setRoleId(roleId);
		role.setName(name);
		return role;
	}
	
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
