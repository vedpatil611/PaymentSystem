package com.barclays.paymentsystem.service;

import com.barclays.paymentsystem.exception.PaymentSystemException;

/**
 * PaymentService - Payment service interface
 * @author Ved
 *
 */
public interface PaymentService {
	/**
	 * autoPayBills - Automatically pays all bills
	 * @return Response string
	 * @throws PaymentSystemException
	 */
	String autoPayBills() throws PaymentSystemException;
	
	/**
	 * manuallyPayBill - Manually pay bill to biller
	 * @param username
	 * @param billerCode
	 * @return Response String
	 * @throws PaymentSystemException
	 */
	String manuallyPayBill(String username, String billerCode) throws PaymentSystemException;
	
	/**
	 * payBill - Pay the given bill
	 * @param bill
	 * @return Response string
	 * @throws PaymentSystemException
	 */
//	String payBill(Bills bill) throws PaymentSystemException;
}
