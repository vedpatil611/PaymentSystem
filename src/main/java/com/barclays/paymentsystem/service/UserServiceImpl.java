package com.barclays.paymentsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barclays.paymentsystem.dto.RegisteredBillerDTO;
import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.RegisteredBiller;
import com.barclays.paymentsystem.entity.User;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.repository.RegisteredBillerRepository;
import com.barclays.paymentsystem.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired 
	private UserRepository userRepo;
	
	
	@Autowired
	private RegisteredBillerRepository registeredBillerRepo;
	
	@Override
	public List<RegisteredBillerDTO> getAllSubscribedBillers(String userId) throws PaymentSystemException{
		Optional<User> user= userRepo.findById(userId);
		
		if(!user.isPresent()) {
			throw new PaymentSystemException("User Not Found");
		}
		
		Account account = user.get().getAccount();
		List<RegisteredBiller> list = registeredBillerRepo.findByAccount(account);
		
		List<RegisteredBillerDTO> list2 = new ArrayList<>();
		for(RegisteredBiller rb: list) {
			list2.add(new RegisteredBillerDTO (rb));
		}
			
		
		return  list2;
	}
	
	
	@Override
	public String subscribeNewBiller(RegisteredBillerDTO registeredBillerDTO) throws PaymentSystemException {
		
		
		//Optional<MasterBiller> masterBiller = masterRepo.findById(billerCode);
		
		RegisteredBiller registerBiller=registeredBillerDTO.toRegisteredBillerEntity();
		//registerBiller.setBillerCode(masterBiller.get());
		RegisteredBiller newRegistered = registeredBillerRepo.save(registerBiller);
		
		return newRegistered.getConsumerNumber();
		
	}


	
}
