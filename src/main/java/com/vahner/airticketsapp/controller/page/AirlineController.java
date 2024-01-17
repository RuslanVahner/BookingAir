package com.vahner.airticketsapp.controller.page;

import com.vahner.airticketsapp.service.interf.AirlineService;
import lombok.RequiredArgsConstructor;
import com.vahner.airticketsapp.entity.Airline;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/airlines")
@RequiredArgsConstructor
public class AirlineController {

    private final AirlineService airlineService;

    @GetMapping("/{id}") //http://localhost:8080/airlines/d3420c7d-8cb0-4c94-9622-edfee61599fc
    public Airline getAirlineById(@PathVariable("id") UUID id) {
        return airlineService.getAirlineById(id);
    }

    @PostMapping("/createAirline") //http://localhost:8080/airlines/createAirline
    public Airline createAirline(@RequestBody Airline airline) {
        return airlineService.create(airline);
    }

//      {
//        "name":"AirlineName",
//        "otherField":"otherValue"
//    }

//    "airlineName": "Ryanair",
//    "airlinePrice": 30.00,
//    "trips": ["AnotherTrip1", "AnotherTrip2"],
//    "airports": ["AnotherAirport1", "AnotherAirport2"

    //"airlineName": "Pegasus Airlines",
    //    "airlinePrice": 50.00,
    //    "trips": null,
    //    "airports": null
}