package com.vahner.airticketsapp.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vahner.airticketsapp.entity.enums.PassengerType;
import com.vahner.airticketsapp.entity.enums.TicketClass;
import com.vahner.airticketsapp.entity.enums.TicketStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TicketDto {
    // Ticket class
    String ticketId;

    BigDecimal price;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    LocalDateTime purchaseTime;

    int ticketNumber;

    TicketClass ticketClass;

    TicketStatus ticketStatus;

    PassengerType type;

    // Flight class
    String nameFlight;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    String departureDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    String arrivalDate;

    String departureAirport;

    String arrivalAirport;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "HH:mm")
    LocalDateTime flightTime;

    // Reservations class
    String reservationId;

    String reservationsReference;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    String reservationsDate;

    // Account class
    String accountId;
}