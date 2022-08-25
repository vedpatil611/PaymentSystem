package com.barclays.paymentsystem.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.barclays.paymentsystem.constants.SystemConstants;
import com.barclays.paymentsystem.dto.RegisteredBillerDTO;
import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.entity.Auth;
import com.barclays.paymentsystem.entity.RegisteredBiller;
import com.barclays.paymentsystem.entity.User;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.repository.AccountRepository;
import com.barclays.paymentsystem.repository.RegisteredBillerRepository;
import com.barclays.paymentsystem.repository.UserRepository;

/**
 * UserServiceImpl - implementation class
 * @author Ved
 *
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RegisteredBillerRepository registeredBillerRepo;
	
	@Autowired
	private AccountRepository accountRepository;
	
	/**
	 * @getAllSubscribedBillers
	 * @param userId
	 * @return List of subscribed billers
	 * @throws PaymentSystemException
	 */
	
	@Override
	public List<RegisteredBillerDTO> getAllSubscribedBillers(String userId) throws PaymentSystemException {
		Optional<User> user = userRepository.findById(userId);

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
	
	/**
	 * @subscribeNewBiller
	 * @param username
	 * @param registeredBillerDTO
	 * @return consumer number of new biller
	 * @throws PaymentSystemException
	 */

	@Override
	public String subscribeNewBiller(String username, RegisteredBillerDTO registeredBillerDTO) throws PaymentSystemException {
		if (registeredBillerDTO == null) 
			throw new PaymentSystemException(SystemConstants.NEW_SUBSCRIPTION_DETAILS_NOT_PROVIDED);
		// Optional<MasterBiller> masterBiller = masterRepo.findById(billerCode);
		Optional<User> user = userRepository.findById(username);

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
	
	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public String login(Auth authenticationDetails,HttpSession session) {
		// TODO Auto-generated method stub
		User user = userRepository.findByUsername(authenticationDetails.getUsername());
		
		if(user != null) {
			if(user.getPassword().equals(authenticationDetails.getPassword())) {
				session.setAttribute("user_id", user.getUsername());
				session.setAttribute("user_role", user.getRole());
				return "user logged in and Username is "+user.getUsername()+"with role"+user.getRole();
			}	
			else
				return "password did not match";
		}
		return "no such user found";
	}

	@Override
	public User getUser(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

	@Override
	public List<User> getAllUser() {
		Iterable<User> users = userRepository.findAll();
		List<User> list = new ArrayList<>();
		users.forEach(user -> list.add(user));
		return list;
	}

	@Override
	public void deleteUser(String username) {
		userRepository.delete(userRepository.findByUsername(username));
	}

}
