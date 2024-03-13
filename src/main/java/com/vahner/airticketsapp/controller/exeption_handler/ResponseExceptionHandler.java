package com.vahner.airticketsapp.controller.exeption_handler;

import com.vahner.airticketsapp.dto.ErrorExtension;
import com.vahner.airticketsapp.dto.ErrorResponse;
import com.vahner.airticketsapp.exception.*;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
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
                    schema = @Schema(implementation = ErrorExtension.class))
    })
    public ResponseEntity<ErrorExtension> handleAccountNotFoundException(AccountNotFoundException ex) {
        ErrorExtension errorExtension = new ErrorExtension(ex.getMessage(), ErrorCode.ACCOUNT_NOT_FOUND);
        return new ResponseEntity<>(errorExtension, HttpStatus.NOT_FOUND);
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

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request
    ) {
        List<ErrorExtension> errorExtensions = ex.getFieldErrors()
                .stream()
                .map(filedError -> new ErrorExtension(filedError.getDefaultMessage(),
                        String.format("invalid_%s", filedError.getField())))
                .collect(Collectors.toList());
        return new ResponseEntity<>(
                new ErrorResponse(ErrorCode.VALIDATION_FAILED, errorExtensions), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<ErrorExtension> handleAuthException(Exception e) {
        return new ResponseEntity<>(new ErrorExtension(
                e.getMessage(),
                ErrorCode.ACCOUNT_NOT_FOUND
        ), HttpStatus.UNAUTHORIZED);
    }

//    @ExceptionHandler(ExpiredJwtException.class)
//    public ResponseEntity<ErrorExtension> handleExpiredJwtException(Exception e) {
//        return new ResponseEntity<>(new ErrorExtension(
//                e.getMessage(),
//               ErrorCode.JWT_EXPIRED
//        ), HttpStatus.UNAUTHORIZED);
//    }
//
//    @ExceptionHandler(MalformedJwtException.class)
//    public ResponseEntity<ErrorExtension> handleMalformedJwtException(Exception e) {
//        return new ResponseEntity<>(new ErrorExtension(
//                e.getMessage(),
//                ErrorCode.JWT_NOT_VALID
//        ), HttpStatus.UNAUTHORIZED);
//    }

}