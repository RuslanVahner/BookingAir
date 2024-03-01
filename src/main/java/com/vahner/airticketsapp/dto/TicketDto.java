package com.vahner.airticketsapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Value
@Builder
@AllArgsConstructor
public class TicketDto {
    String uuid;
    BigDecimal price;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    LocalDateTime data;
    String service;
    String type;

    String nameTrips;
    String tripsType;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    LocalDateTime flightTime;

    String address;
    String nameAirPort;

    String owner;
}