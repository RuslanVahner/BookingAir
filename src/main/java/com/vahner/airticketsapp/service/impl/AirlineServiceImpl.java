package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.entity.Airline;
import com.vahner.airticketsapp.repository.AirlineRepository;
import com.vahner.airticketsapp.service.interf.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    @Override
    public Airline getAirlineById(UUID uuid) {
        return airlineRepository.getAirlineById(uuid);
    }

    @Override
    public List<Airline> getAllAirlines() {
        return airlineRepository.findAll();
    }

    @Override
    public Airline createAirline(Airline airline) {
        return airlineRepository.save(airline);
    }

}