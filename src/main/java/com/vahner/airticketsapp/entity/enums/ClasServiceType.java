package com.vahner.airticketsapp.entity.enums;

import lombok.Getter;

@Getter
public enum ClasServiceType {
    ECONOMY("Economy"),
    BUSINESS("Business"),
    FIRST("First");

    private final String service;

    ClasServiceType(String service) {
        this.service = service;
    }
}