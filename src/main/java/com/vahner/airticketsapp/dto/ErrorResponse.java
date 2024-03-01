package com.vahner.airticketsapp.dto;

import lombok.Value;

import java.util.List;

@Value
public class ErrorResponse {
    String errorCode;
    List<ErrorExtension> errorExtensions;
}