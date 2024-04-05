package com.vahner.airticketsapp.controller;

import com.vahner.airticketsapp.dto.BookingCreateDTO;
import com.vahner.airticketsapp.entity.Booking;
import com.vahner.airticketsapp.service.interf.BookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "Booking Controller")
@RestController
@RequestMapping("/api/bookings")
@RequiredArgsConstructor
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    @Operation(summary = "get all bookings",
            description = "retrieve a list of all bookings",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successfully returned list of bookings")
            })
    public ResponseEntity<List<Booking>> getAllBookings() {
        List<Booking> bookings = bookingService.getAllBookings();
        return ResponseEntity.ok().body(bookings);
    }

    @GetMapping("/{id}")
    @Operation(summary = "get a booking by id",
            description = "returns a booking from the database for the given id",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Booking found"),
                    @ApiResponse(responseCode = "404", description = "No booking was found with this id")
            })
    public ResponseEntity<Booking> getBookingById(@PathVariable UUID id) {
        Booking booking = bookingService.getBookingById(id);
        return ResponseEntity.ok().body(booking);
    }

    @PostMapping("/createBooking")
    @Operation(summary = "create new booking",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "creat a new booking with userId and flightId;",
                    required = true,
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = BookingCreateDTO.class)))
    )
    public ResponseEntity<Booking> createBooking(@Valid @RequestBody BookingCreateDTO bookingCreateDTO) {
        Booking booking = bookingService.createBooking(bookingCreateDTO);
        return ResponseEntity.ok(booking);
    }

    @PutMapping("/updateBooking/{id}")
    @Operation(summary = "update booking",
            description = "updates the information of an existing booking",
            responses = {
                    @ApiResponse(responseCode = "200", description = "successfully updated the booking")
            })
    public ResponseEntity<Booking> updateBooking(@PathVariable UUID id, @RequestBody Booking bookingDetails) {
        Booking updatedBooking = bookingService.updateBooking(id, bookingDetails);
        return ResponseEntity.ok().body(updatedBooking);
    }

    @DeleteMapping("/deleteBooking/{id}")
    @Operation(summary = "delete booking",
            description = "deletion of an booking by the specified id")
    @ApiResponse(responseCode = "204", description = "Successfully deleted the booking")
    public ResponseEntity<Void> deleteBooking(@PathVariable UUID id) {
        bookingService.deleteBooking(id);
        return ResponseEntity.noContent().build();
    }
}