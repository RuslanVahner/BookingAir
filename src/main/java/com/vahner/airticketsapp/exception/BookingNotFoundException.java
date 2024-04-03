package com.vahner.airticketsapp.exception;

public class BookingNotFoundException extends RuntimeException {
    public  BookingNotFoundException(String massage){
        super(massage);
    }
}
