package com.vahner.airticketsapp.exception;

public class TripsNotFoundException extends RuntimeException {
    public TripsNotFoundException(String uuid) {
        super(String.format("Trips not found" + uuid));
    }
}
