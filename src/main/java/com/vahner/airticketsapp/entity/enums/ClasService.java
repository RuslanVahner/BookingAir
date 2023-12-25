package com.vahner.airticketsapp.entity.enums;

import lombok.Getter;

@Getter
public enum ClasService {
    ECONOMY("Economy"),
    BUSINESS("Business"),
    FIRST("First");

    private final String service;

    ClasService(String service) {
        this.service = service;
    }
}