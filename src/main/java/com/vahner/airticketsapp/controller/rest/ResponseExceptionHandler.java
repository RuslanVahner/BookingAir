package com.vahner.airticketsapp.controller.rest;


import com.vahner.airticketsapp.exception.AccountNotFoundException;
import com.vahner.airticketsapp.exception.PassengerNotFoundException;
import com.vahner.airticketsapp.exception.TicketNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {PassengerNotFoundException.class})
    protected ResponseEntity<Object> handlePassengerNotFound(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Ticket not found";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {AccountNotFoundException.class})
    protected ResponseEntity<Object> handleAccountNotFound(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Account not found";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(value = {TicketNotFoundException.class})
    protected ResponseEntity<Object> handleTicketNotFound(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = "Ticket not found";
        return handleExceptionInternal(ex, bodyOfResponse,
                new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }
}
