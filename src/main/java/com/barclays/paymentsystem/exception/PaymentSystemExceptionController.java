package com.barclays.paymentsystem.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.barclays.paymentsystem.utils.ErrorInfo;

/**
 * PaymentSystemExceptionController - Rest exception handler
 * @author Ved
 *
 */
@RestControllerAdvice
public class PaymentSystemExceptionController {
	
	/**
	 * exceptionHandler - Catch any uncaught exceptions
	 * @param exception
	 * @return ErrorInfo response
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorInfo> exceptionHandler(Exception exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage("Request cannot be processed");
		error.setErrorCode(HttpStatus.ACCEPTED.value());
		error.setTimestamp(LocalDateTime.now());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	/**
	 * paymentSystemExceptionHandler - Catch PaymentSystemException
	 * @param exception
	 * @return ErrorInfo response
	 */
	@ExceptionHandler(PaymentSystemException.class)
	public ResponseEntity<ErrorInfo> paymentSystemExceptionHandler(PaymentSystemException exception) {
		ErrorInfo error = new ErrorInfo();
		error.setErrorMessage(exception.getMessage());
		error.setTimestamp(LocalDateTime.now());
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorInfo>(error, HttpStatus.NOT_FOUND);
	}
}
