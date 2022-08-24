package com.barclays.paymentsystem.service;

<<<<<<< HEAD
import com.barclays.paymentsystem.dto.AccountDTO;
import com.barclays.paymentsystem.entity.Account;
import com.barclays.paymentsystem.exception.PaymentSystemException;

public interface PaymentService {
	
    public void manualPayment(AccountDTO accountDTO) throws PaymentSystemException;
=======
import com.barclays.paymentsystem.exception.PaymentSystemException;

/**
 * PaymentService - Payment service
 * @author Ved
 *
 */
public interface PaymentService {
	/**
	 * autoPayBills - Automatically pays all bills
	 * @return Response string
	 * @throws PaymentSystemException
	 */
	String autoPayBills() throws PaymentSystemException;
	
	/**
	 * manuallyPayBill - Manually pay bill to biller
	 * @param username
	 * @param billerCode
	 * @return Response String
	 * @throws PaymentSystemException
	 */
	String manuallyPayBill(String username, String billerCode) throws PaymentSystemException;
	
	/**
	 * payBill - Pay the given bill
	 * @param bill
	 * @return Response string
	 * @throws PaymentSystemException
	 */
//	String payBill(Bills bill) throws PaymentSystemException;
>>>>>>> 58607f1eccdc5f8eb90676d49369219203670b6a
}
