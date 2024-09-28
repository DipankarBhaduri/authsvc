package com.mingleHub.authsvc.aspects;

import com.mingleHub.authsvc.constants.ErrorTitle;
import com.mingleHub.authsvc.exceptions.BaseException;
import com.mingleHub.authsvc.exceptions.DuplicateEmailException;
import com.mingleHub.authsvc.exceptions.RestTemplateException;
import com.mingleHub.authsvc.exceptions.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.zalando.problem.Problem;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(
            {
                    DuplicateEmailException.class,
                    UserNotFoundException.class
            }
    )
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public Problem onDuplicateEmailException (BaseException e) {
        return Problem.builder()
					   .withTitle(ErrorTitle.DUPLICATE_EMAIL.toString())
					   .withDetail("Method Not Allowed")
					   .build();
    }

    @ExceptionHandler(
            {
                    RestTemplateException.class
            }
    )
    @ResponseStatus(HttpStatus.METHOD_FAILURE)
    public Problem onExternalConnectionError (BaseException e) {
	  return Problem.builder()
					 .withTitle(ErrorTitle.CLIENT_ERROR.toString())
					 .withDetail("Method Failure")
					 .build();
    }
}