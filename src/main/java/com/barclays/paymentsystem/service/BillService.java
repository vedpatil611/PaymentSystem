package com.barclays.paymentsystem.service;

import com.barclays.paymentsystem.dto.BillDTO;
import com.barclays.paymentsystem.dto.MasterBillerDTO;
import com.barclays.paymentsystem.exception.PaymentSystemException;

public interface BillService {
    String addNewBill(BillDTO billDTO) throws PaymentSystemException;
}
