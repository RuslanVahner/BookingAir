package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.FlightCreateDTO;
import com.vahner.airticketsapp.dto.FlightUpdateDTO;
import com.vahner.airticketsapp.entity.Flight;
import com.vahner.airticketsapp.mapper.FlightMapper;
import com.vahner.airticketsapp.repository.FlightRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Test class for FlightServiceImpl")
class FlightServiceImplTest {
    @Mock
    private FlightRepository flightRepository;

    @Mock
    private FlightMapper flightMapper;

    @InjectMocks
    private FlightServiceImpl flightService;

    private UUID flightId;
    private Flight flight;
    private FlightCreateDTO flightCreateDTO;
    private FlightUpdateDTO flightUpdateDTO;

    @BeforeEach
    void setUp() {
        flightId = UUID.fromString("d5b877ac-cf47-43c6-8d6a-8d0978005b13");
        flight = new Flight();
        flight.setId(flightId);

        flightCreateDTO = new FlightCreateDTO();
        flightCreateDTO.setFlightNumber("123");
        flightCreateDTO.setDepartureAirport("Airport Test1");
        flightCreateDTO.setArrivalAirport("Airport Test2");
        flightCreateDTO.setDepartureTime(LocalDateTime.now());
        flightCreateDTO.setArrivalTime(LocalDateTime.now().plusHours(2));
        flightCreateDTO.setPrice(200.0);

        flightUpdateDTO = new FlightUpdateDTO();
        flightUpdateDTO.setFlightNumber("321");
        flightUpdateDTO.setDepartureAirport("Airport Test3");
        flightUpdateDTO.setArrivalAirport("Airport Test4");
        flightUpdateDTO.setDepartureTime(LocalDateTime.now());
        flightUpdateDTO.setArrivalTime(LocalDateTime.now().plusHours(3));
        flightUpdateDTO.setPrice(300.0);
    }

    @Test
    void createFlightTest() {
        when(flightMapper.flightCreateDto(any(FlightCreateDTO.class))).thenReturn(flight);
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);

        Flight savedFlight = flightService.createFlight(flightCreateDTO);

        verify(flightMapper).flightCreateDto(any(FlightCreateDTO.class));
        verify(flightRepository).save(any(Flight.class));
        assertNotNull(savedFlight);
    }


    @Test
    void updateFlightTest() {
        when(flightRepository.findById(flightId)).thenReturn(java.util.Optional.of(flight));
        when(flightRepository.save(any(Flight.class))).thenReturn(flight);

        Flight updatedFlight = flightService.updateFlight(flightId, flightUpdateDTO);

        verify(flightRepository).findById(flightId);
        verify(flightRepository).save(any(Flight.class));
        assertNotNull(updatedFlight);
    }

    @Test
    void getAllFlightsTest() {
        when(flightRepository.findAll()).thenReturn(List.of(flight));

        List<Flight> flights = flightService.getAllFlights();

        verify(flightRepository).findAll();
        assertFalse(flights.isEmpty());
    }

    @Test
    void getFlightByIdTest() {
        when(flightRepository.findById(flightId)).thenReturn(java.util.Optional.of(flight));

        Flight foundFlight = flightService.getFlightById(flightId);

        verify(flightRepository).findById(flightId);
        assertNotNull(foundFlight);
    }

    @Test
    void deleteFlightTest() {
        when(flightRepository.findById(flightId)).thenReturn(java.util.Optional.of(flight));
        doNothing().when(flightRepository).delete(any(Flight.class));

        flightService.deleteFlight(flightId);

        verify(flightRepository).findById(flightId);
        verify(flightRepository).delete(any(Flight.class));
    }
}