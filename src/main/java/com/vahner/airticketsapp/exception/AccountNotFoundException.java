package com.vahner.airticketsapp.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String owner) {
        super(String.format("Account not found" + owner));
    }
}
