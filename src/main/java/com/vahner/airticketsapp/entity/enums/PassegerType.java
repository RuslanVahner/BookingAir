package com.vahner.airticketsapp.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum PassegerType {
    ADULT("ADULT"),
    CHILD("CHILD");

    private final String type;

    PassegerType(String type) {
        this.type = type;
    }

    @JsonValue
    public String getValue(){
        return type;
    }
}