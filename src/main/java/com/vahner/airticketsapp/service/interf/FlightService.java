package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.dto.FlightCreateDTO;
import com.vahner.airticketsapp.dto.FlightUpdateDTO;
import com.vahner.airticketsapp.entity.Flight;

import java.util.List;
import java.util.UUID;

public interface FlightService {
    List<Flight> getAllFlights();

    Flight getFlightById(UUID id);

    Flight createFlight(FlightCreateDTO flightCreateDTO);

    Flight updateFlight(UUID id, FlightUpdateDTO flightUpdateDTO);

    void deleteFlight(UUID id);
}
