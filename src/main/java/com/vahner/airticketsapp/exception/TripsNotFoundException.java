package com.vahner.airticketsapp.exception;

public class TripsNotFoundException extends RuntimeException {
    public TripsNotFoundException(String massage) {
        super(massage);
    }
}
