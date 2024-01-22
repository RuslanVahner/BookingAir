package com.vahner.airticketsapp.controller.page;

import com.vahner.airticketsapp.service.interf.AirlineService;
import lombok.RequiredArgsConstructor;
import com.vahner.airticketsapp.entity.Airline;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/airlines")
@RequiredArgsConstructor
public class AirlineController {

    private final AirlineService airlineService;

    @GetMapping("/airline")
    public ResponseEntity<List<Airline>> getAll() {
        return new ResponseEntity<>(airlineService.getAllAirlines(), HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public Airline getAirlineById(@PathVariable UUID id) {
        return airlineService.getAirlineById(id);
    }

    @GetMapping
    public List<Airline> getAllAirlines() {
        return airlineService.getAllAirlines();
    }

    @PostMapping("/create")
    public Airline createAirline(@RequestBody Airline airline) {
        return airlineService.createAirline(airline);

    }
}
