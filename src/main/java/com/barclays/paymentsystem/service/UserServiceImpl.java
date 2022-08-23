package com.barclays.paymentsystem.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.barclays.paymentsystem.dto.RegisteredBillerDTO;
import com.barclays.paymentsystem.entity.MasterBiller;
import com.barclays.paymentsystem.entity.RegisteredBiller;
import com.barclays.paymentsystem.repository.MasterBillerRepository;
import com.barclays.paymentsystem.repository.RegisteredBillerRepository;

@Service(value="userService")
public class UserServiceImpl implements UserService {
	
	//@Autowired 
	//private UserRepository userRepo;
	
	
	@Autowired 
	private MasterBillerRepository masterRepo;
	@Autowired
	private RegisteredBillerRepository registeredBillerRepo;
	
	public List<MasterBiller> getAllBillers() {
		
		return  (List<MasterBiller>) masterRepo.findAll();
	}
	
	public String subscribeNewBiller(RegisteredBillerDTO registeredBillerDTO,String billerCode) {
		
		
		Optional<MasterBiller> masterBiller = MasterBillerRepository.findById(billerCode);
		
		RegisteredBiller registerBiller=registeredBillerDTO.toRegisteredBillerEntity();
		registerBiller.setBillerCode(masterBiller.get());
		
		registeredBillerRepo.save(registeredBillerDTO);
	}
	
}
