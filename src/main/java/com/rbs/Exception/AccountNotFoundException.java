package com.rbs.Exception;

public class AccountNotFoundException extends Exception {
    public AccountNotFoundException(String errorMessage) {
        super(errorMessage);
    }
}
