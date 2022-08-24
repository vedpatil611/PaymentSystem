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

/*
 * MasterBillerController - Rest api for Master Biller details
 */
@RestController
public class MasterBillerController {
	
	@Autowired
	MasterBillerService masterBillerService;
	
	/**
	 * getAllMasterBiller - Get Master Biller list
	 * @param Master Biller - Master Biller list
	 * @return list 
	 * @throws PaymentSystemException
	 */
	
	@GetMapping("/biller/list")
	public ResponseEntity<List<MasterBillerDTO>> getAllMasterBiller() throws PaymentSystemException {
		List<MasterBillerDTO> list = masterBillerService.getAllMasterBiller();
		return new ResponseEntity<>(list, HttpStatus.OK);
	}
	
	/**
	 * getMasterBillerById - Get Master Biller By Id
	 * @param masterBillerId - Master Biller Id
	 * @return Master Biller
	 * @throws PaymentSystemException
	 */
	
	@GetMapping("/biller/{masterBillerId}")
	public ResponseEntity<MasterBillerDTO> getMasterBillerById(@PathVariable String masterBillerId) throws PaymentSystemException {
		MasterBillerDTO masterBiller = masterBillerService.getMasterBiller(masterBillerId);
		return new ResponseEntity<>(masterBiller, HttpStatus.OK);
	}
	 
	/**
	 * addNewMasterBiller - Add new  master biller to list
	 * @param Master Biller - Master Biller list
	 * @return New Master Biller Code 
	 * @throws PaymentSystemException
	 */
	
	@PostMapping("/biller/new")
	public ResponseEntity<String> addNewMasterBiller(@RequestBody MasterBillerDTO masterBillerDTO) throws PaymentSystemException {
		String newCode = masterBillerService.addNewMasterBiller(masterBillerDTO);
		return new ResponseEntity<>(newCode, HttpStatus.OK);
	}
}
