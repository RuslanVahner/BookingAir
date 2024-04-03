package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.FlightCreateDTO;
import com.vahner.airticketsapp.dto.FlightUpdateDTO;
import com.vahner.airticketsapp.entity.Flight;
import com.vahner.airticketsapp.mapper.FlightMapper;
import com.vahner.airticketsapp.repository.FlightRepository;
import com.vahner.airticketsapp.service.interf.FlightService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    @Override
    public Flight createFlight(FlightCreateDTO flightCreateDTO) {
        log.info("Creating flight: {}", flightCreateDTO);
        Flight flight = flightMapper.flightCreateDto(flightCreateDTO);
        Flight savedFlight = flightRepository.save(flight);
        log.info("Flight created successfully with ID: {}", savedFlight.getId());
        return savedFlight;
    }

    @Override
    public Flight updateFlight(UUID id, FlightUpdateDTO flightUpdateDTO) {
        log.info("Updating flight with ID: {}", id);
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Flight not found with id: " + id));

        flight.setFlightNumber(flightUpdateDTO.getFlightNumber());
        flight.setDepartureAirport(flightUpdateDTO.getDepartureAirport());
        flight.setArrivalAirport(flightUpdateDTO.getArrivalAirport());
        flight.setDepartureTime(flightUpdateDTO.getDepartureTime());
        flight.setArrivalTime(flightUpdateDTO.getArrivalTime());
        flight.setPrice(flightUpdateDTO.getPrice());

        log.info("Flight with ID: {} updated successfully", id);
        return flightRepository.save(flight);
    }

    @Override
    public List<Flight> getAllFlights() {
        log.info("Retrieving all flights");
        return flightRepository.findAll();
    }

    @Override
    public Flight getFlightById(UUID id) {
        log.info("Retrieving flight with ID: {}", id);
        return flightRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Flight not found with id: " + id));
    }

    @Override
    public void deleteFlight(UUID id) {
        log.info("Deleting flight with ID: {}", id);
        Flight flight = getFlightById(id);
        log.info("Flight with ID: {} deleted successfully", id);
        flightRepository.delete(flight);

    }
}
