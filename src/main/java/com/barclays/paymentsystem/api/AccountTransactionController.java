package com.barclays.paymentsystem.api;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.barclays.paymentsystem.constants.SystemConstants;
import com.barclays.paymentsystem.dto.AccountTransactionDTO;
import com.barclays.paymentsystem.exception.PaymentSystemException;
import com.barclays.paymentsystem.service.AccountTransactionService;
import com.barclays.paymentsystem.utils.TransactionExporter;
import com.barclays.paymentsystem.constants.*;
@RestController
public class AccountTransactionController {

	@Autowired(required = false)
	AccountTransactionService managerControllerService;

	// get all account details
	@GetMapping(PaymentSystemControllerConstants.GET_ACCOUNT_TRANSACTIONS)
	public ResponseEntity<List<AccountTransactionDTO>> getAllAccountTransaction(@PathVariable String username)
			throws PaymentSystemException {
		return new ResponseEntity<>(managerControllerService.findAll(username), HttpStatus.OK);
	}

	@GetMapping(PaymentSystemControllerConstants.GET_DOWNLOAD_ACCOUNT_TRANSACTIONS)
	public void downloadAllAccountTransaction(@PathVariable String username, HttpServletResponse response)
			throws PaymentSystemException {

		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		TransactionExporter exporter = new TransactionExporter(managerControllerService.findAll(username));
		try {
			exporter.export(response);
		} catch (Exception e) {
			throw new PaymentSystemException(SystemConstants.FAILED_TO_EXPORT_EXCEL_RESPONSE);
		}
	}

	@GetMapping(PaymentSystemControllerConstants.GET_ACCOUNT_TRANSACTIONS_STARTDATE)
	public ResponseEntity<List<AccountTransactionDTO>> getAllAccountTransactionByStartDate(
			@PathVariable String username, @RequestParam LocalDateTime startDate, @RequestParam LocalDateTime endDate)
			throws PaymentSystemException {

		return new ResponseEntity<>(managerControllerService.findAllBetweenDate(username, startDate, endDate),
				HttpStatus.OK);
	}

	@GetMapping(PaymentSystemControllerConstants.GET_DOWNLOAD_ACCOUNT_TRANSACTIONS_STARTDATE )
	public void downloadAccountTransactionByStartDate(@PathVariable String username,
			@RequestParam LocalDateTime startDate,
			@RequestParam LocalDateTime endDate,
			HttpServletResponse response)
			throws PaymentSystemException {

		response.setContentType("application/octet-stream");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=users_" + currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		TransactionExporter exporter = new TransactionExporter(
				managerControllerService.findAllBetweenDate(username, startDate, endDate));
		try {
			exporter.export(response);
		} catch (Exception e) {
			throw new PaymentSystemException(SystemConstants.FAILED_TO_EXPORT_EXCEL_RESPONSE);
		}
	}
}
