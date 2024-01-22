package com.vahner.airticketsapp.entity.enums;

import lombok.Getter;

@Getter
public enum ClasServiceType {
    ECONOMY("ECONOMY"),
    BUSINESS("BUSINESS"),
    FIRST("FIRST");

    private final String service;

    ClasServiceType(String service) {
        this.service = service;
    }
}