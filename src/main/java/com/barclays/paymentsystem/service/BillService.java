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
	String addNewBill(BillDTO billDTO) throws PaymentSystemException;

	List<BillDTO> findAll(String billerCode) throws PaymentSystemException;

	public List<BillDTO> findAllBetweenDate(String username, LocalDate from, LocalDate to) throws PaymentSystemException;

	List<BillDTO> getAllBills() throws PaymentSystemException;
}
