package com.barclays.paymentsystem.exception;

/**
 * PaymentSystemException - Custom exception
 * @author Ved
 *
 */
public class PaymentSystemException extends Exception {

	private static final long serialVersionUID = 1L;

	public PaymentSystemException(String message) {
		super(message);
	}
}
