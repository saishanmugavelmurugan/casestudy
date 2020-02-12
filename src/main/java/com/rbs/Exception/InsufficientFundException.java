package com.rbs.Exception;

public class InsufficientFundException extends Exception {
    public InsufficientFundException(String errorMessage) {
        super(errorMessage);
    }
}
