package com.vahner.airticketsapp.exception;

public class AccountNotFoundException extends RuntimeException{
    public AccountNotFoundException(String massage) {
        super(massage);
    }
}
