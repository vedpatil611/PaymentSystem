package com.barclays.paymentsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MasterBiller {
	@Id
	String billerCode;
	String name;

	public String getBillerCode() {
		return billerCode;
	}

	public void setBillerCode(String billerCode) {
		this.billerCode = billerCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
