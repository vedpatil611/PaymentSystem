package com.barclays.paymentsystem.service;

import java.time.LocalDate;
import java.util.List;

import com.barclays.paymentsystem.dto.BillDTO;
import com.barclays.paymentsystem.exception.PaymentSystemException;

/**
 * BIllService - Interface for Bill service api
 * @author PB3C
 *
 */


public interface BillService {
	/**
	 * autoPayBills - Automatically pays all bills
	 * 
	 * @return Response string
	 * @throws PaymentSystemException
	 */
	String autoPayBills() throws PaymentSystemException;

	/**
	 * manuallyPayBill - Manually pay bill to biller
	 * 
	 * @param username
	 * @param billerCode
	 * @return Response String
	 * @throws PaymentSystemException
	 */
	String manuallyPayBill(String username, String billerCode) throws PaymentSystemException;

	String addNewBill(BillDTO billDTO) throws PaymentSystemException;

	List<BillDTO> findAll(String billerCode) throws PaymentSystemException;

	public List<BillDTO> findAllBetweenDate(String username, LocalDate from, LocalDate to) throws PaymentSystemException;
}
