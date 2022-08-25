package com.barclays.paymentsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.barclays.paymentsystem.dto.AccountDTO;
import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barclays.paymentsystem.constants.SystemConstants;
import com.barclays.paymentsystem.dto.MasterBillerDTO;
import com.barclays.paymentsystem.entity.MasterBiller;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.repository.MasterBillerRepository;

/**
 * MasterBillerServiceImp - MasterBillerService implementation class
 * 
 * @author Ved
 *
 */
@Service
@Transactional
public class MasterBillerServiceImpl implements MasterBillerService {

	@Autowired
	MasterBillerRepository masterBillerRepository;
	
	/**
	 * @getAllMasterBiller
	 * @param null
	 * @return List of Biller
	 * @throws PaymentSystemException
	 */

	@Autowired
	AccountRepository accountRepository;

	@Override
	public List<MasterBillerDTO> getAllMasterBiller() throws PaymentSystemException {
		Iterable<MasterBiller> list = masterBillerRepository.findAll();
		List<MasterBillerDTO> billerList = new ArrayList<>();

		list.forEach(biller -> billerList.add(new MasterBillerDTO(biller)));
		return billerList;
	}
	
	/**
	 * @getAllMasterBiller
	 * @param billerCode
	 * @return new biller
	 * @throws PaymentSystemException
	 */

	@Override
	public List<AccountDTO> getAllAccount() throws PaymentSystemException {
		Iterable<Account> list = accountRepository.findAll();
		List<AccountDTO> accountList = new ArrayList<>();

		list.forEach(account -> accountList.add(new AccountDTO(account)));
		return accountList;
	}

	@Override
	public MasterBillerDTO getMasterBiller(String billerCode) throws PaymentSystemException {
		Optional<MasterBiller> opt = masterBillerRepository.findById(billerCode);
		if (!opt.isPresent())
			throw new PaymentSystemException(SystemConstants.BILLER_NOT_FOUND_RESPONSE);

		return new MasterBillerDTO(opt.get());
	}
	
	/**
	 * @addNewMasterBiller
	 * @param masterBillerDTO
	 * @return Biller Code of New Biller
	 * @throws PaymentSystemException
	 */

	@Override
	public String addNewMasterBiller(MasterBillerDTO masterBillerDTO) {
		MasterBiller masterBiller = masterBillerDTO.toEntity();
		MasterBiller newBiller = masterBillerRepository.save(masterBiller);
		return newBiller.getBillerCode();
	}

}
