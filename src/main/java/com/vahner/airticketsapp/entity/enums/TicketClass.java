package com.vahner.airticketsapp.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TicketClass {
    ECONOMY("ECONOMY"),
    BUSINESS("BUSINESS"),
    FIRST("FIRST");

    private final String ticketClass;

    TicketClass(String service) {
        this.ticketClass = service;
    }

    @JsonValue
    public String getValueTicketClass(){
        return ticketClass;
    }
}