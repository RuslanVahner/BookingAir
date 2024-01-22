package com.vahner.airticketsapp.entity.enums;

import lombok.Getter;

@Getter
public enum TripsType {
    ACTIVE("ACTIVE"),
    CANCELLED("CANCELLED"),
    TRANSFERRED("TRANSFERRED");

    private final String tripsType;

    TripsType(String tripsType) {
        this.tripsType = tripsType;
    }
}