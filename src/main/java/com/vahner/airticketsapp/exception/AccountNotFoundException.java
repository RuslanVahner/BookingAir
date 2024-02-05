package com.vahner.airticketsapp.exception;


public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String message) {
        super("Account id is not found" + message);
    }


}
