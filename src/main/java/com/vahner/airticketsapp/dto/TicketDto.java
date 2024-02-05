package com.vahner.airticketsapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class TicketDto {

    String id;
    BigDecimal price;
    LocalDateTime data;
    String service;
    String type;

    String nameTrips;
    String tripsType;
    LocalDateTime flightTime;
    LocalDateTime arrival;
    LocalDateTime departure;

    String address;
    String nameAirPort;

    String owner;
}
