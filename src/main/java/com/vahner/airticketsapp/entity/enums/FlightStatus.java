package com.vahner.airticketsapp.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum FlightStatus {
    ACTIVE("ACTIVE"),
    CANCELLED("CANCELLED"),
    INACTIVE("INACTIVE");

    private final String FlightStatus;

    FlightStatus(String tripsType) {
        this.FlightStatus = tripsType;
    }

    @JsonValue
    public String getValue(){
        return FlightStatus;
    }
}