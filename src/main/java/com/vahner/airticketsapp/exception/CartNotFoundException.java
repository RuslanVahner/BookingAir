package com.vahner.airticketsapp.exception;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String massage) {
        super(massage);
    }
}


