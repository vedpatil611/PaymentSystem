package com.barclays.paymentsystem.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.barclays.paymentsystem.dto.AccountDTO;
import com.barclays.paymentsystem.exception.PaymentSystemException;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Override
	public void manualPayment(AccountDTO accountDTO) throws PaymentSystemException {
		// TODO Auto-generated method stub
}
