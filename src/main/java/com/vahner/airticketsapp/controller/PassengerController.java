package com.vahner.airticketsapp.controller;

import com.vahner.airticketsapp.dto.PassengerDto;
import com.vahner.airticketsapp.dto.ShortPassengerDto;
import com.vahner.airticketsapp.service.interf.PassengerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Passenger Controller")
@Validated
@RestController
@RequestMapping("/api/passenger")
@RequiredArgsConstructor
public class PassengerController {

    private final PassengerService passengerService;

    @GetMapping("/{id}")
    @Operation(summary = "Get a passenger by ID",
            description = "Returns a passenger from the database for the given ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Passenger found"),
                    @ApiResponse(responseCode = "404", description = "No passenger was found with this ID")
            })
    public ResponseEntity<PassengerDto> getPassengerById(@PathVariable String id) {
        PassengerDto passengerDto = passengerService.getPassengerById(id);
        return new ResponseEntity<>(passengerDto, HttpStatus.OK);
    }

    @GetMapping
    @Operation(summary = "Get all passengers",
            description = "Retrieve a list of all passengers",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully returned list of passengers")
            })
    public ResponseEntity<List<ShortPassengerDto>> getAllPassengers() {
        List<ShortPassengerDto> passengersList = passengerService.getAllPassengers();
        return ResponseEntity.ok(passengersList);
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new passenger",
            description = "Creates a new passenger based on the provided information",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Successfully created the passenger")
            })
    public ResponseEntity<PassengerDto> createPassenger(@Valid @RequestBody PassengerDto passengerDto) {
        PassengerDto createdPassenger = passengerService.createPassenger(passengerDto);
        return new ResponseEntity<>(createdPassenger, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a passenger",
            description = "Updates the information of an existing passenger",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully updated the passenger")
            })
    public ResponseEntity<Void> updatePassenger(@PathVariable String id, @Valid @RequestBody PassengerDto passengerDto) {
        passengerService.updatePassenger(id, passengerDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a passenger",
            description = "Deletes a passenger by the specified ID",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Successfully deleted the passenger")
            })
    public ResponseEntity<Void> deletePassengerById(@PathVariable String id) {
        passengerService.deletePassengerById(id);
        return ResponseEntity.noContent().build();
    }
}