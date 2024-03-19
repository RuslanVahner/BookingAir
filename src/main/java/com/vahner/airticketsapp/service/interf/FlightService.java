package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.dto.FlightDto;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
     List<FlightDto> searchFlights(String departureAirport, String arrivalAirport, LocalDate departureDate);
}
