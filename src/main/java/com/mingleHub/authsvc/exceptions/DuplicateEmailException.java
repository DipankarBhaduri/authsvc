package com.mingleHub.authsvc.exceptions;

public class DuplicateEmailException extends BaseException{
    public DuplicateEmailException(String message){
        super(message);
    }
}
