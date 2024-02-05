package com.vahner.airticketsapp.exception;

public class PassengerNotFoundException extends RuntimeException {
    public PassengerNotFoundException(String message) {
        super("Passenger id is not found" + message);
    }
}
