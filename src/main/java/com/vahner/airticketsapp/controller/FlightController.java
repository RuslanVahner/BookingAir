package com.vahner.airticketsapp.controller;

import com.vahner.airticketsapp.dto.FlightDto;
import com.vahner.airticketsapp.entity.Flight;
import com.vahner.airticketsapp.service.interf.FlightService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Flight")
@Validated
@RestController
@RequestMapping("/api/flight")
@RequiredArgsConstructor
public class FlightController {

    private final FlightService flightService;

    @GetMapping("/search")
    @Operation(
            summary = "Search flights",
            description = "Search for flights based on departure location, arrival location, and departure date.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Successfully found flights",
                            content = @Content(schema = @Schema(implementation = Flight.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid search parameters"),
                    @ApiResponse(responseCode = "404", description = "No flights found matching criteria")
            }
    )
    public ResponseEntity<List<FlightDto>> searchFlights(@RequestParam String departureAirport,
                                                      @RequestParam String arrivalAirport,
                                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                      LocalDate departureDate) {
        List<FlightDto> flights = flightService.searchFlights(departureAirport, arrivalAirport, departureDate);
        return ResponseEntity.ok(flights);
    }
}
