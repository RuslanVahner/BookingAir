package com.vahner.airticketsapp.exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String massage) {
        super(massage);
    }
}
