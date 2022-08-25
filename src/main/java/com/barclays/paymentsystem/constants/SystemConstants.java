package com.barclays.paymentsystem.constants;

/*
 * SystemConstants - static variables initialization
 * @author PB3C
 */
public interface SystemConstants {
	public static final String ACCOUNT_NOT_FOUND_RESPONSE = "Account not found";
	
	public static final String MASTER_BILLER_NOT_FOUND_RESPONSE = "Biller not found";
	public static final String MASTER_BILLER_CODE_ALREADY_EXISTS_RESPONSE = "Master biller already exists";
	public static final String MASTER_BILLER_DELETED_SUCCESSFULLY_RESPONSE = "Master biller deleted successfully";
	
	public static final String REGISTERED_BILLER_NOT_FOUND_REPONSE = "Registered biller not found";
	public static final String NEW_SUBSCRIPTION_DETAILS_NOT_PROVIDED = "New Subscription details not provided";
	
	public static final String BILL_NOT_FOUND_RESPONSE = "Biller not found";
	public static final String BILL_PAYMENT_SUCCESS_RESPONSE = "Bill paid successfully";
	public static final String BILL_PAYMENT_FAILURE_RESPONSE = "Failed to pay bill";
	public static final String FAILED_TO_CREATE_BILL_RESPONSE = "Failed to create bill";
	
	public static final String NO_PENDING_BILL_RESPONSE = "No pending bills";
	public static final String PENDING_BILLS_SUCCESSFULLY_PAID_RESPONSE = "Pending bills successfully paid";
	public static final String INSUFFICENT_BALANCE_RESPONSE = "Insufficient bank balance";
		
	public static final String USER_NOT_FOUND_RESPONSE = "User not found";
	
	public static final String FAILED_TO_EXPORT_EXCEL_RESPONSE = "Failed to export excel";
	
	public static final String BILL_DATA_NOT_PROVIDED = "Bill data not provided";
	
	public static final String ROLE_NOT_FOUND_EXCEPTION = "Role id not found";
}
