package com.mingleHub.authsvc.exceptions;

public class BaseException extends RuntimeException{
    public BaseException() {
    }
    public BaseException(String message) {
        super(message);
    }
}
