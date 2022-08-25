package com.barclays.paymentsystem.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.barclays.paymentsystem.utils.ErrorInfo;

/**
 * PaymentSystemExceptionController - Rest exception handler
 * @author PB3C
 *
 */
@RestControllerAdvice
public class PaymentSystemExceptionController {
	
	private Logger LOGGER = LoggerFactory.getLogger(getClass());
	
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
		LOGGER.error(exception.getMessage());
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
