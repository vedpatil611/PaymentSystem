package com.barclays.paymentsystem.dto;

import com.barclays.paymentsystem.entity.MasterBiller;

/**
 * MasterBillerDTO - MasterBiller Data Transfer Object
 * 
 * @author PB3C
 *
 */
public class MasterBillerDTO {
	String billerCode;
	String name;

	/**
	 * Create new MasterBillerDTO instance
	 */
	public MasterBillerDTO() {
	}

	/**
	 * Create new MasterBillerDTO instance from MasterBiller entity
	 * 
	 * @param masterBiller - MasterBiller entity
	 */
	public MasterBillerDTO(MasterBiller masterBiller) {
		this.billerCode = masterBiller.getBillerCode();
		this.name = masterBiller.getName();
	}

	/**
	 * Convert MasterBillerDTO to MasterBiller entity
	 * 
	 * @return MasterBiller entity
	 */
	public MasterBiller toEntity() {
		MasterBiller masterBiller = new MasterBiller();
		masterBiller.setBillerCode(billerCode);
		masterBiller.setName(name);
		return masterBiller;
	}

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
