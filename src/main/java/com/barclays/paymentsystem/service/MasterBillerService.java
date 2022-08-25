package com.barclays.paymentsystem.service;

import java.util.List;

import com.barclays.paymentsystem.dto.AccountDTO;
import com.barclays.paymentsystem.dto.MasterBillerDTO;
import com.barclays.paymentsystem.exception.PaymentSystemException;

/**
 * MasterBillerService - MasterBiller table service
 * @author Ved
 *
 */
public interface MasterBillerService {
	/**
	 * getAllMasterBiller - get list of all available biller
	 * @return list of master billers
	 */
	List<MasterBillerDTO> getAllMasterBiller() throws PaymentSystemException;

	List<AccountDTO> getAllAccount() throws PaymentSystemException;
	/**
	 * getMasterBiller - Get Master Biller by biller code
	 * @param billerCode - unique biller code
	 * @return Master biller data
	 */
	MasterBillerDTO getMasterBiller(String billerCode) throws PaymentSystemException;
	
	/**
	 * Create a new master biller
	 * @param masterBillerDTO - Data for new master biller
	 * @return Id of newly created master biller
	 */
	String addNewMasterBiller(MasterBillerDTO masterBillerDTO) throws PaymentSystemException;
}
