package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.entity.Airline;
import com.vahner.airticketsapp.repository.AirlineRepository;
import com.vahner.airticketsapp.service.interf.AirlineService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AirlineServiceImpl implements AirlineService {

    private final AirlineRepository airlineRepository;

    @Override
    public Airline getAirlineById(UUID id) {
        return airlineRepository.getAirlineById(id);
    }

    @Override
    public Airline create(Airline airline) {
        return airlineRepository.save(airline);
    }
}