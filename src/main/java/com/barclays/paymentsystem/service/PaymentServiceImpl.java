package com.barclays.paymentsystem.service;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.barclays.paymentsystem.dto.AccountDTO;
import com.barclays.paymentsystem.exception.PaymentSystemException;

@Service
@Transactional
public class PaymentServiceImpl implements PaymentService {

	@Override
	public void manualPayment(AccountDTO accountDTO) throws PaymentSystemException {
		// TODO Auto-generated method stub
		try {
			accountService.transferBalances(request);
			
			TransferResult result = new TransferResult();
			result.setAccountFromId(request.getAccountFromId());
			result.setBalanceAfterTransfer(accountService.checkBalance(request.getAccountFromId()));
			
			return new ResponseEntity<>(result, HttpStatus.ACCEPTED);
		} catch (AccountNotExistException | OverDraftException e) {
			log.error("Fail to transfer balances, please check with system administrator.");
			throw e;
		} catch (CheckBalanceException cbEx) {
			log.error("Fail to check balances after transfer, please check with system administrator.");
			throw cbEx;
		}
	}

}
