package com.barclays.paymentsystem.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.paymentsystem.dto.MasterBillerDTO;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.service.MasterBillerService;

@RestController
public class MasterBillerController {
	
	@Autowired
	MasterBillerService masterBillerService;
	
	@GetMapping("/biller/list")
	public ResponseEntity<List<MasterBillerDTO>> getAllMasterBiller() throws PaymentSystemException {
		List<MasterBillerDTO> list = masterBillerService.getAllMasterBiller();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	@GetMapping("/biller/{masterBillerId}")
	public ResponseEntity<MasterBillerDTO> getMasterBillerById(@PathVariable String masterBillerId) throws PaymentSystemException {
		MasterBillerDTO masterBiller = masterBillerService.getMasterBiller(masterBillerId);
		return new ResponseEntity<>(masterBiller, HttpStatus.OK);
	}
	
	@PostMapping("/biller/new")
	public ResponseEntity<String> addNewMasterBiller(@RequestBody MasterBillerDTO masterBillerDTO) throws PaymentSystemException {
		String newCode = masterBillerService.addNewMasterBiller(masterBillerDTO);
		return new ResponseEntity<>(newCode, HttpStatus.OK);
	}
}