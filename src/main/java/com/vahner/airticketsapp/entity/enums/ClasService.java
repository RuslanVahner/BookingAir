package com.vahner.airticketsapp.entity.enums;

import lombok.Getter;

@Getter
public enum ClasService {
    Economy("Economy"),
    Business("Business"),
    First("First");

    private final String service;

    ClasService(String service) {
        this.service = service;
    }
}