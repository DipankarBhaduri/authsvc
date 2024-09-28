package com.mingleHub.authsvc.aspects;

import com.mingleHub.authsvc.constants.ErrorTitle;
import com.mingleHub.authsvc.exceptions.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class KycExceptionControllerAdvice {
	
	@ExceptionHandler(
			{
					InvalidNumberException.class,
			}
	)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public Problem onInvalidNumberException (BaseException e) {
		return new Problem()
					   .setTitle(ErrorTitle.WRONG_MOBILE_NUMBER.toString())
					   .setMessage(e.getLocalizedMessage());
	}
	
	@ExceptionHandler(
			{
					InvalidRequestException.class,
					InvalidOTPException.class,
					PhoneAlreadyVerifiedException.class
			}
	)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	public Problem onInvalidRequestException (BaseException e) {
		return new Problem()
					   .setTitle(ErrorTitle.INVALID_REQUEST.toString())
					   .setMessage(e.getLocalizedMessage());
	}
}
