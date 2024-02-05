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
@RequestMapping("/api/airline")
@RequiredArgsConstructor
public class AirlineController {

    private final AirlineService airlineService;

    /**
     * -@GetMapping("/airliners")-
     * ==
     * GetMapping: <a href=" http://localhost:8080/api/airline/airlines ">...</a>
     * Status: Error;
     */

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<Airline> getAllAirlines() {
        return airlineService.getAllAirlines();
    }

    /**
     * -@GetMapping("/{uuid}")-
     * Retrieving an existing airline by its ID in the database;
     * GetMapping: <a href=" http://localhost:8080/api/airline/df86a563-bbc4-11ee-b62b-744ca1631356 ">...</a>
     * Status: 200;
     */

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public Airline getAirlineById(@PathVariable("uuid") UUID uuid) {
        return airlineService.getAirlineById(uuid);
    }

    /**
     * -@PostMapping("/create")-
     * Creation of a new airline;
     * PostMapping: <a href=" http://localhost:8080/api/airline/create ">...</a>
     * Status: 200;
     */

    @PostMapping("/create")
    public Airline createAirline(@RequestBody Airline airline) {
        return airlineService.createAirline(airline);
    }

    /**
     * -@DeleteMapping("/deleteAirline/{uuid}")-
     * Delete airline;
     * <a href=" http://localhost:8080/api/airline/deleteAirline/ ">...</a>
     * Status: 200;
     */

    @DeleteMapping("/deleteAirline/{uuid}")
    public void deleteAirline(@PathVariable("uuid") UUID uuid){
        airlineService.deleteAirline(uuid);
    }
}