package com.vahner.airticketsapp.controller.page;

import com.vahner.airticketsapp.service.interf.AirlineService;
import lombok.RequiredArgsConstructor;
import com.vahner.airticketsapp.entity.Airline;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/airline")
@RequiredArgsConstructor
public class AirlineController {

    private final AirlineService airlineService;


    @GetMapping("/airliners")
    @ResponseStatus(HttpStatus.OK)
    public List<Airline> getAllAirlines() {
        return airlineService.getAllAirlines();
    }

    /**
     * Working code
     * Retrieving an existing airline by its ID in the database;
     * GetMapping: <a href=" http://localhost:8080/api/airline/8084c1a1-b969-11ee-b62b-744ca1631356 ">...</a>
     * Status: 200;
     */

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public Airline getAirlineById(@PathVariable("uuid") UUID uuid) {
        return airlineService.getAirlineById(uuid);
    }

    /**
     * Creation of a new airline;
     * PostMapping: <a href=" http://localhost:8080/api/airline/create ">...</a>
     * Status: 500
     */

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.OK)
    public Airline createAirline(@RequestBody Airline airline) {
        return airlineService.createAirline(airline);
    }
}