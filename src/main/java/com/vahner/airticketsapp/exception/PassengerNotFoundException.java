package com.vahner.airticketsapp.exception;

public class PassengerNotFoundException extends IllegalArgumentException {
    public PassengerNotFoundException(String uuid) {
        super("Passenger with id not found" + uuid);
    }
}