package com.vahner.airticketsapp.exception;

public class AirlineNotFoundException extends RuntimeException {

    public AirlineNotFoundException(String message) {
        super(message);

    }
}