package com.barclays.paymentsystem.constants;

public interface PaymentSystemControllerConstants {
	public static final String GET_ACCOUNT_TRANSACTIONS = "/{username}/accountTransaction";
	public static final String GET_DOWNLOAD_ACCOUNT_TRANSACTIONS = "/{username}/accountTransaction/download";
	public static final String GET_ACCOUNT_TRANSACTIONS_STARTDATE = "/{username}/accountTransactionBetween";
	public static final String GET_DOWNLOAD_ACCOUNT_TRANSACTIONS_STARTDATE = "/{username}/accountTransactionBetween/download";
	public static final String NEW_BILL = "/bill/new";
	public static final String GET_BILL_USERNAME = "/user/{username}/bills";
	public static final String AUTOPAY_BILLS = "/cron/payall";
	public static final String GET_MASTERBILLER_LIST = "/biller/list";
	public static final String GET_ID_BILLER = "/biller/{masterBillerId}";
	public static final String ADD_NEW_BILLER = "/biller/new";
	public static final String PAY_BILL_BILLERCODE = "/user/{username}/paybill/{billerCode}";
	public static final String GET_SUBSCRIBED_BILLERS = "/users/{username}/allsubscriptions";
	public static final String SUBSCRIBE_NEW_BILLER = "/users/{username}/subscribe";
}
