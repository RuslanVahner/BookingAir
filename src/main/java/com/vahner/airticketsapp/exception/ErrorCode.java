package com.vahner.airticketsapp.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorCode {
    public static final String ACCOUNT_NOT_FOUND = "account_not_found";
    public static final String JWT_EXPIRED = "token_expired";
    public static final String JWT_NOT_VALID = "token_is_not_valid";
    public static final String VALIDATION_FAILED = "invalid_path_variable";
}