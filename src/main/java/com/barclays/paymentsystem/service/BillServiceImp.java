package com.barclays.paymentsystem.service;

import com.barclays.paymentsystem.dto.BillDTO;
import com.barclays.paymentsystem.entity.Bills;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class BillServiceImp implements BillService{

    @Autowired
    BillRepository billRepository;

    @Override
    public String addNewBill(BillDTO billDTO) throws PaymentSystemException {
        Bills bills = billDTO.toEntity();
        Bills newBill= billRepository.save(bills);
        return newBill.getConsumerNumber();
    }
}
