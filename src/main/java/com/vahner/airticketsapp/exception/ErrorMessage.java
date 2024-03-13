package com.vahner.airticketsapp.exception;

import lombok.experimental.UtilityClass;

@UtilityClass
public class ErrorMessage {
    public static final String M_ACCOUNT_NOT_FOUND = "Account with id %s not found";
    public static final String M_PASSEGER_NOT_FOUND = "Passenger with id %s not found";
    public static final String M_WRONG_CREDENTIALS = "Wrong credentials";
    public static final String M_INVALID_TOKEN = "Invalid token";
}