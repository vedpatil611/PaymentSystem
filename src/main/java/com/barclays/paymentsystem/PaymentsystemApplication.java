package com.barclays.paymentsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Payment system application offer api for manager and account holder.
 * Manager can add new master billers to list and create new list
 * Account holders can subscribe to a service and pay them bill
 * @author Ved
 *
 */
@SpringBootApplication
public class PaymentsystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(PaymentsystemApplication.class, args);
	}

}
