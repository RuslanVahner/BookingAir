package com.vahner.airticketsapp.service.interf;


import com.vahner.airticketsapp.dto.TripsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TripsService {
    TripsDto getTripsById(String uuid);
    ResponseEntity<List<TripsDto>> findAll();

    ResponseEntity<TripsDto> create(TripsDto tripsDto);

    TripsDto updateTrips(String uuid, TripsDto tripsDto);

    void deleteTrips(String uuid);
}