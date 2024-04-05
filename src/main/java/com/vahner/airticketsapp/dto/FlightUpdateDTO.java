package com.vahner.airticketsapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FlightUpdateDTO {
    private String flightNumber;
    private String departureAirport;
    private String arrivalAirport;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime departureTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime arrivalTime;
    private Double price;
}
