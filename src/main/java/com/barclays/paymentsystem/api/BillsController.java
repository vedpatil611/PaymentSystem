package com.barclays.paymentsystem.api;

import com.barclays.paymentsystem.dto.BillDTO;
import com.barclays.paymentsystem.dto.MasterBillerDTO;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.service.BillService;
import com.barclays.paymentsystem.service.MasterBillerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BillsController {
    @Autowired
    BillService billService;

    @PostMapping("/bill/new")
    public ResponseEntity<String> addNewBill(@RequestBody BillDTO billDTO) throws PaymentSystemException {
        String newCode = billService.addNewBill(billDTO);
        return new ResponseEntity<>(newCode, HttpStatus.OK);
    }
}
