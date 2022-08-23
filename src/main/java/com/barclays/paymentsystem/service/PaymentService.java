package com.barclays.paymentsystem.service;

import com.barclays.paymentsystem.dto.AccountDTO;
import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.exception.PaymentSystemException;

public interface PaymentService {
	
    public void manualPayment(AccountDTO accountDTO) throws PaymentSystemException;
}
