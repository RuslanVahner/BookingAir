package com.vahner.airticketsapp.exception;

public class PassengerNotFoundException extends IllegalArgumentException {
    public PassengerNotFoundException(String massage) {
        super(massage);
    }
}