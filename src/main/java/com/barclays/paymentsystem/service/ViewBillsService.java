package com.barclays.paymentsystem.service;

import com.barclays.paymentsystem.dto.AccountDTO;
import com.barclays.paymentsystem.dto.BillDTO;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.entity.Bills;

import java.util.List;


public interface ViewBillsService {
	List<BillDTO> findAll(String billerCode) throws PaymentSystemException;

}
