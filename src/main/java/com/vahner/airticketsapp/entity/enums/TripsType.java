package com.vahner.airticketsapp.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
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

    @JsonValue
    public String getValue(){
        return tripsType;
    }
}