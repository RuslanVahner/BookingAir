package com.vahner.airticketsapp.exception;

public class AuthException extends RuntimeException {
    public AuthException(String message) {
        super(message);
    }
}