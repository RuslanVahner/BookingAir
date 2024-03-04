package com.vahner.airticketsapp.controller.exeption_handler;

import com.vahner.airticketsapp.dto.ErrorExtension;
import com.vahner.airticketsapp.dto.ErrorResponse;
import com.vahner.airticketsapp.exception.AccountNotFoundException;
import com.vahner.airticketsapp.exception.PassengerNotFoundException;
import com.vahner.airticketsapp.exception.TicketNotFoundException;
import com.vahner.airticketsapp.exception.TripsNotFoundException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {

    private ResponseEntity<ErrorResponse> handleException(HttpStatus status, String errorCode, String message) {
        ErrorResponse errorResponse = new ErrorResponse(errorCode, List.of(new ErrorExtension(message, errorCode)));
        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        return new ResponseEntity<>("An error occurred: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(AccountNotFoundException.class)
    @ApiResponse(responseCode = "404", description = "Not Found", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))
    })
    public ResponseEntity<ErrorResponse> handleAccountNotFoundException(AccountNotFoundException ex) {
        return handleException(HttpStatus.NOT_FOUND, "ACCOUNT_NOT_FOUND", ex.getMessage());
    }

    @ExceptionHandler(PassengerNotFoundException.class)
    @ApiResponse(responseCode = "404", description = "Not Found", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))
    })
    public ResponseEntity<ErrorResponse> handlePassengerNotFoundException(PassengerNotFoundException ex) {
        return handleException(HttpStatus.NOT_FOUND, "PASSENGER_NOT_FOUND", ex.getMessage());
    }

    @ExceptionHandler(TicketNotFoundException.class)
    @ApiResponse(responseCode = "404", description = "Not Found", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))
    })
    public ResponseEntity<ErrorResponse> handleTicketNotFoundException(TicketNotFoundException ex) {
        return handleException(HttpStatus.NOT_FOUND, "TICKET_NOT_FOUND", ex.getMessage());
    }

    @ExceptionHandler(TripsNotFoundException.class)
    @ApiResponse(responseCode = "404", description = "Not Found", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorResponse.class))
    })
    public ResponseEntity<ErrorResponse> handleTripsNotFoundException(TripsNotFoundException ex) {
        return handleException(HttpStatus.NOT_FOUND, "TRIPS_NOT_FOUND", ex.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + " " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ErrorResponse errorResponse = new ErrorResponse("VALIDATION_ERROR", errors.stream()
                .map(error -> new ErrorExtension(error, "VALIDATION_ERROR"))
                .collect(Collectors.toList()));
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}