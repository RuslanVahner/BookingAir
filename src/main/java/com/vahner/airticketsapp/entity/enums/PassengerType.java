package com.vahner.airticketsapp.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum PassengerType {
    ADULT("ADULT"),
    CHILD("CHILD");

    private final String type;

    PassengerType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getValue(){
        return type;
    }
}