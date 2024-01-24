package com.vahner.airticketsapp.controller.page;

import com.vahner.airticketsapp.entity.Trips;
import com.vahner.airticketsapp.repository.TripRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/trip")
@RequiredArgsConstructor
public class TripController {

    private final TripRepository tripRepository;

    @GetMapping("{uuid}")
    public Trips getTripsById(@PathVariable("uuid") UUID uuid) {
        return tripRepository.getReferenceById(uuid);
    }
}

