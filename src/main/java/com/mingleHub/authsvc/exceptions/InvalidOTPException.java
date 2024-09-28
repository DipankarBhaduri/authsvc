package com.mingleHub.authsvc.exceptions;

public class InvalidOTPException extends BaseException{
	public InvalidOTPException(String message){
		super(message);
	}
}