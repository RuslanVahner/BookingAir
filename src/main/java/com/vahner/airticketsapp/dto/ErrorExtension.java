package com.vahner.airticketsapp.dto;

import lombok.Value;

@Value
public class ErrorExtension {
    String message;
    String errorCode;

}