package com.vahner.airticketsapp.exception;


public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String message) {
        super("Account not found" + message);
    }


}
