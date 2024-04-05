package com.vahner.airticketsapp.controller;

import com.vahner.airticketsapp.dto.FlightCreateDTO;
import com.vahner.airticketsapp.dto.FlightUpdateDTO;
import com.vahner.airticketsapp.entity.Flight;
import com.vahner.airticketsapp.service.interf.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Flight Controller")
@Validated
@RestController
@RequestMapping("/api/flight")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @PostMapping("/createFlight")
    @Operation(summary = "create new flight",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "create a new flight with flightNumber, departureAirport, arrivalAirport," +
                            " departureTime, arrivalTime, price",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = FlightCreateDTO.class)))
    )
    public ResponseEntity<Flight> createFlight(@Valid @RequestBody FlightCreateDTO flightCreateDTO) {
        Flight createdFlight = flightService.createFlight(flightCreateDTO);
        return new ResponseEntity<>(createdFlight, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "get all flights",
            description = "retrieve a list of all flights",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successfully returned list of flights")
            })
    public ResponseEntity<List<Flight>> getAllFlights() {
        List<Flight> flights = flightService.getAllFlights();
        return ResponseEntity.ok(flights);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get a flight by id",
            description = "returns a flight from the database for the given id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Flight found"),
                    @ApiResponse(responseCode = "404", description = "No flight was found with this id")
            })
    public ResponseEntity<Flight> getFlightById(@PathVariable UUID id) {
        Flight flight = flightService.getFlightById(id);
        return ResponseEntity.ok(flight);
    }

    @PutMapping("/updateFlight/{id}")
    @Operation(summary = "update flight",
            description = "updates the information of an existing flight",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successfully updated the flight")
            })
    public ResponseEntity<Flight> updateFlight(@PathVariable UUID id, @RequestBody FlightUpdateDTO flightUpdateDTO) {
        Flight updatedFlight = flightService.updateFlight(id, flightUpdateDTO);
        return ResponseEntity.ok(updatedFlight);
    }

    @DeleteMapping("/deleteFlight/{id}")
    @Operation(summary = "delete flight",
            description = "deletion of an flight by the specified id")
    @ApiResponse(responseCode = "204", description = "Successfully deleted the flight")
    public ResponseEntity<Void> deleteFlight(@PathVariable UUID id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}
