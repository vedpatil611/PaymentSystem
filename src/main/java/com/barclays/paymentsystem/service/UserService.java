package com.barclays.paymentsystem.service;


import java.util.List;

import com.barclays.paymentsystem.dto.RegisteredBillerDTO;
import com.barclays.paymentsystem.entity.MasterBiller;


public interface UserService {

	
	public List<MasterBiller> getAllBillers();
	
	public String subscribeNewBiller(RegisteredBillerDTO registeeredBillerDTO);
	
}
