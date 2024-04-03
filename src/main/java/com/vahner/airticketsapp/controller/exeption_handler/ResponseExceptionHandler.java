package com.vahner.airticketsapp.controller.exeption_handler;

import com.vahner.airticketsapp.dto.ErrorExtension;
import com.vahner.airticketsapp.dto.ErrorResponse;
import com.vahner.airticketsapp.exception.BookingNotFoundException;
import com.vahner.airticketsapp.exception.ErrorCode;
import com.vahner.airticketsapp.exception.FlightNotFoundException;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ResponseExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(Exception.class)
    public final ResponseEntity<Object> handleAllExceptions(Exception ex) {
        List<ErrorExtension> errorExtensions = List.of(new ErrorExtension(ex.getLocalizedMessage(), "GENERAL_ERROR"));
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.DATABASE_ERROR, errorExtensions);
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    @ApiResponse(responseCode = "404", description = "Not Found", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorExtension.class))
    })
    public final ResponseEntity<Object> handleUserNotFoundException(UsernameNotFoundException ex) {
        List<ErrorExtension> errorExtensions = List.of(new ErrorExtension(ex.getLocalizedMessage(), ErrorCode.USER_NOT_FOUND));
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.USER_NOT_FOUND, errorExtensions);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(FlightNotFoundException.class)
    @ApiResponse(responseCode = "404", description = "Not Found", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorExtension.class))
    })
    public final ResponseEntity<Object> handleFlightNotFoundException(FlightNotFoundException ex) {
        List<ErrorExtension> errorExtensions = List.of(new ErrorExtension(ex.getLocalizedMessage(), ErrorCode.FLIGHT_NOT_FOUND));
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.FLIGHT_NOT_FOUND, errorExtensions);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookingNotFoundException.class)
    @ApiResponse(responseCode = "404", description = "Not Found", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ErrorExtension.class))
    })
    public final ResponseEntity<Object> handleBookingNotFoundException(FlightNotFoundException ex) {
        List<ErrorExtension> errorExtensions = List.of(new ErrorExtension(ex.getLocalizedMessage(), ErrorCode.BOOKING_NOT_FOUND));
        ErrorResponse errorResponse = new ErrorResponse(ErrorCode.BOOKING_NOT_FOUND, errorExtensions);
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }


    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
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

}
