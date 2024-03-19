package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.FlightDto;
import com.vahner.airticketsapp.mapper.FlightMapper;
import com.vahner.airticketsapp.repository.FlightRepository;
import com.vahner.airticketsapp.service.interf.FlightService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class FlightServiceImpl implements FlightService {

    private final FlightRepository flightRepository;
    private final FlightMapper flightMapper;

    /**
     * Searches for flights based on departure airport, arrival airport, and departure date.
     * Поиск рейсов на основе аэропорта отправления, аэропорта прибытия и даты отправления.
     *
     * @param departureAirport Аэропорт отправления.
     * @param arrivalAirport   Аэропорт прибытия.
     * @param departureDate    Дата отправления.
     * @return Список DTO рейсов, соответствующих заданным критериям поиска.
     */

    @Override
    public List<FlightDto> searchFlights(String departureAirport, String arrivalAirport, LocalDate departureDate) {
        log.info("Searching for flights from {} to {} on {}", departureAirport, arrivalAirport, departureDate);
        return flightRepository
                .findByDepartureAirportAndArrivalAirportAndDepartureDate(departureAirport, arrivalAirport, departureDate)
                .stream()
                .map(flightMapper::toDto)
                .collect(Collectors.toList());
    }
}