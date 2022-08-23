package com.barclays.paymentsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barclays.paymentsystem.constants.SystemConstants;

import com.barclays.paymentsystem.entity.AccountTransaction;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.repository.ManagerControllerRepository;


@Service
public class ManagerControllerServiceImp implements ManagerControllerService {

    @Autowired
    private ManagerControllerRepository managercontrollerRepository;
    @Override
    public List < AccountTransaction > findAll() {
        return managercontrollerRepository.findAll();
    }
}
