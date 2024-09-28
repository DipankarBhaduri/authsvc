package com.mingleHub.authsvc.exceptions;

public class UserNotFoundException extends BaseException {
    public UserNotFoundException(String message){
        super(message);
    }
}
