package com.barclays.paymentsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.barclays.paymentsystem.constants.SystemConstants;
import com.barclays.paymentsystem.dto.MasterBillerDTO;
import com.barclays.paymentsystem.exception.PaymentSystemException;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class MasterBillerServiceTest {
	
	@Autowired
	MasterBillerService masterBillerService;
	
	static MasterBillerDTO masterBiller;
	
	@BeforeAll
	static void initialize() {
		masterBiller = new MasterBillerDTO();
		masterBiller.setBillerCode("T001");
		masterBiller.setName("Test biller");
	}
	
	@Test
	@Order(1)
	void addMasterBiller() throws PaymentSystemException {
		MasterBillerDTO masterBiller = new MasterBillerDTO();
		masterBiller.setBillerCode("T001");
		masterBiller.setName("Test biller");
		
		String code = masterBillerService.addNewMasterBiller(masterBiller);
		Assertions.assertEquals(code, masterBiller.getBillerCode());
		
		Assertions.assertThrows(PaymentSystemException.class, () -> masterBillerService.addNewMasterBiller(masterBiller));
	}
	
	@Test
	@Order(2)
	void listMasterBiller() throws PaymentSystemException {
		List<MasterBillerDTO> list = masterBillerService.getAllMasterBiller();
		Assertions.assertNotEquals(list.size(), 0);
	}
	
	@Test
	@Order(3)
	void findMasterBiller() throws PaymentSystemException {
		MasterBillerDTO biller = masterBillerService.getMasterBiller(masterBiller.getBillerCode());
		Assertions.assertEquals(biller.getName(), masterBiller.getName());
		
		Assertions.assertThrows(PaymentSystemException.class, () -> masterBillerService.getMasterBiller("T002"));
	}
	
	@Test
	@Order(4)
	void deleteMasterBiller() throws PaymentSystemException {
		String message = masterBillerService.deleteMasterBiller(masterBiller.getBillerCode());
		assertEquals(message, SystemConstants.MASTER_BILLER_DELETED_SUCCESSFULLY_RESPONSE);
		assertThrows(PaymentSystemException.class, () -> masterBillerService.deleteMasterBiller(masterBiller.getBillerCode()));
	}
}
