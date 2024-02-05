package com.vahner.airticketsapp.exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String m) {
        super("Ticket not found" + m);
    }
}
