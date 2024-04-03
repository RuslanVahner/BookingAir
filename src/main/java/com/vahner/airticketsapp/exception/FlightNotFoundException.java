package com.vahner.airticketsapp.exception;

public class FlightNotFoundException extends RuntimeException {
    public FlightNotFoundException(String massage){
        super(massage);
    }

}
