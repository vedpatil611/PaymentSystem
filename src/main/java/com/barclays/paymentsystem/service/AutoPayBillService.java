package com.barclays.paymentsystem.service;

import com.barclays.paymentsystem.entity.Bills;
import com.barclays.paymentsystem.exception.PaymentSystemException;

/**
 * AutoPayBillService - Bill auto pay service
 * @author Ved
 *
 */
public interface AutoPayBillService {
	String autoPayBills() throws PaymentSystemException;
	String payBill(Bills bill) throws PaymentSystemException;
}
