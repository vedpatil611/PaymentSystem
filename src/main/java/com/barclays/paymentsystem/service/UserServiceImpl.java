package com.barclays.paymentsystem.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barclays.paymentsystem.constants.SystemConstants;
import com.barclays.paymentsystem.dto.BillDTO;
import com.barclays.paymentsystem.dto.RegisteredBillerDTO;
import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.BillStatus;
import com.barclays.paymentsystem.entity.Bill;
import com.barclays.paymentsystem.entity.RegisteredBiller;
import com.barclays.paymentsystem.entity.User;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.repository.AccountRepository;
import com.barclays.paymentsystem.repository.BillRepository;
import com.barclays.paymentsystem.repository.RegisteredBillerRepository;
import com.barclays.paymentsystem.repository.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private RegisteredBillerRepository registeredBillerRepo;

	@Autowired
	BillRepository billRepository;
	
	@Autowired
	AccountRepository accountRepository;
	
	@Override
	public List<RegisteredBillerDTO> getAllSubscribedBillers(String userId) throws PaymentSystemException {
		Optional<User> user = userRepo.findById(userId);

		if (!user.isPresent()) {
			throw new PaymentSystemException("User Not Found");
		}

		String accountNo = user.get().getAccount().getAccountNo();
		Account account = accountRepository.findById(accountNo).get();
	
		List<RegisteredBiller> list = registeredBillerRepo.findByAccount(account);

		List<RegisteredBillerDTO> list2 = new ArrayList<>();
		for (RegisteredBiller rb : list) {
			list2.add(new RegisteredBillerDTO(rb));
		}

		return list2;
	}

	@Override
	public String subscribeNewBiller(String username, RegisteredBillerDTO registeredBillerDTO) throws PaymentSystemException {
		if (registeredBillerDTO == null) 
			throw new PaymentSystemException(SystemConstants.NEW_SUBSCRIPTION_DETAILS_NOT_PROVIDED);
		// Optional<MasterBiller> masterBiller = masterRepo.findById(billerCode);
		Optional<User> user = userRepo.findById(username);

		if (!user.isPresent()) {
			throw new PaymentSystemException("User Not Found");
		}
//
//		Account account = user.get().getAccount();
		String accountNo = user.get().getAccount().getAccountNo();
		Account account = accountRepository.findById(accountNo).get();
		registeredBillerDTO.setAccount(account);
		
		RegisteredBiller registerBiller = registeredBillerDTO.toRegisteredBillerEntity();
		// registerBiller.setBillerCode(masterBiller.get());
		RegisteredBiller newRegistered = registeredBillerRepo.save(registerBiller);

//		BillDTO billDTO = new BillDTO();
//		billDTO.setBillerCode(newRegistered.getBillerCode());
//		billDTO.setConsumerNumber(newRegistered.getConsumerNumber());
//		billDTO.setAmount(newRegistered.getAmount());
//		billDTO.setDueDate(LocalDate.now().plusMonths(1));
//		billDTO.setStatus(BillStatus.PENDING);
//	
//		Bills bill = billRepository.save(billDTO.toEntity());
		
//		if (bill == null)
//			throw new PaymentSystemException(SystemConstants.FAILED_TO_CREATE_BILL_RESPONSE);
		
		// TODO: Send mail
		
		return newRegistered.getConsumerNumber();

	}

}
