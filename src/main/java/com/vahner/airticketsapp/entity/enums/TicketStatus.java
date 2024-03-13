package com.vahner.airticketsapp.entity.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
public enum TicketStatus {
    RESERVED("RESERVED"),
    PAID("PAID"),
    CANCELLED("CANCELLED");

    private final String ticketStatus;

    TicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }

    @JsonValue
    public String getValueTicketStatus(){
        return ticketStatus;
    }
}
