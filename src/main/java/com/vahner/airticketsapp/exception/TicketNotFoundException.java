package com.vahner.airticketsapp.exception;

public class TicketNotFoundException extends RuntimeException {
    public TicketNotFoundException(String uuid) {
        super(String.format("Ticket not found" + uuid));
    }
}
