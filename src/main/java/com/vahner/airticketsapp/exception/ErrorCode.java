package com.vahner.airticketsapp.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorCode {
    public static final String USER_NOT_FOUND = "user_not_found";
    public static final String FLIGHT_NOT_FOUND = "flight_not_found";
    public static final String BOOKING_NOT_FOUND = "booking_not_found";
    public static final String DATABASE_ERROR = "database_error";
    public static final String VALIDATION_FAILED = "invalid_path_variable";
}