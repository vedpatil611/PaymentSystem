package com.barclays.paymentsystem.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MasterBiller {
	@Id
	String billerCode;
	String name;
}
