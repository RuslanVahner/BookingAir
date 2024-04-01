package com.vahner.airticketsapp.dto;

import lombok.Data;

@Data
public class RefreshJwtRequest {
    private String refreshToken;
}