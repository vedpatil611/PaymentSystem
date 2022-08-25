package com.barclays.paymentsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
	public MasterBillerDTO getMasterBiller(String billerCode) throws PaymentSystemException {
		Optional<MasterBiller> opt = masterBillerRepository.findById(billerCode);
		if (!opt.isPresent())
			throw new PaymentSystemException(SystemConstants.MASTER_BILLER_NOT_FOUND_RESPONSE);

		return new MasterBillerDTO(opt.get());
	}
	
	/**
	 * @addNewMasterBiller
	 * @param masterBillerDTO
	 * @return Biller Code of New Biller
	 * @throws PaymentSystemException
	 */

	@Override
	public String addNewMasterBiller(MasterBillerDTO masterBillerDTO) throws PaymentSystemException {
		Optional<MasterBiller> opt = masterBillerRepository.findById(masterBillerDTO.getBillerCode());
		if (opt.isPresent())
			throw new PaymentSystemException(SystemConstants.MASTER_BILLER_CODE_ALREADY_EXISTS_RESPONSE);
		
		MasterBiller masterBiller = masterBillerDTO.toEntity();
		MasterBiller newBiller = masterBillerRepository.save(masterBiller);
		return newBiller.getBillerCode();
	}

	@Override
	public String deleteMasterBiller(String masterBillerCode) throws PaymentSystemException {
		Optional<MasterBiller> opt = masterBillerRepository.findById(masterBillerCode);
		if (!opt.isPresent())
			throw new PaymentSystemException(SystemConstants.MASTER_BILLER_NOT_FOUND_RESPONSE);
		
		masterBillerRepository.delete(opt.get());
		
		return SystemConstants.MASTER_BILLER_DELETED_SUCCESSFULLY_RESPONSE;
	}

	
}
