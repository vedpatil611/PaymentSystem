package com.barclays.paymentsystem.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
/**
 * MasterBiller - MasterBiller Entity mapped to MasterBiller table in database
 * @author PB3C
 *
 */
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

	@Override
	public int hashCode() {
		return Objects.hash(billerCode, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MasterBiller other = (MasterBiller) obj;
		return Objects.equals(billerCode, other.billerCode) && Objects.equals(name, other.name);
	}

}
