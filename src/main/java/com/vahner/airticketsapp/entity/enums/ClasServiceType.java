package com.vahner.airticketsapp.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
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

    @JsonValue
    public String getValue(){
        return service;
    }
}