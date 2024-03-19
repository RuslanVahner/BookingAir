package com.vahner.airticketsapp.controller;

import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.service.interf.TicketService;
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

@Tag(name = "Ticket")
@Validated
@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/{id}")
    @Operation(summary = "Get a ticket by ID",
            description = "Returns a ticket from the database for the given ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Ticket found"),
                    @ApiResponse(responseCode = "404", description = "No ticket was found with this ID")
            })
    public ResponseEntity<TicketDto> getTicketById(@PathVariable String id) {
        TicketDto ticketDto = ticketService.getTicketById(id);
        return ResponseEntity.ok(ticketDto);
    }

    @GetMapping
    @Operation(summary = "Get all tickets",
            description = "Retrieve a list of all tickets",
            responses = @ApiResponse(responseCode = "200", description = "Successfully returned list of tickets"))
    public ResponseEntity<List<TicketDto>> getAllTickets() {
        List<TicketDto> tickets = ticketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @PostMapping("/create")
    @Operation(summary = "Create a new ticket",
            description = "Creates a new ticket based on the provided information",
            responses = @ApiResponse(responseCode = "201", description = "Successfully created the ticket"))
    public ResponseEntity<TicketDto> createTicket(@Valid @RequestBody TicketDto ticketDto) {
        TicketDto createdTicket = ticketService.createTicket(ticketDto);
        return new ResponseEntity<>(createdTicket, HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Update a ticket",
            description = "Updates the information of an existing ticket",
            responses = @ApiResponse(responseCode = "200", description = "Successfully updated the ticket"))
    public ResponseEntity<TicketDto> updateTicket(@PathVariable String id, @Valid @RequestBody TicketDto ticketDto) {
        ticketService.updateTicket(id, ticketDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a ticket",
            description = "Deletes a ticket by the specified ID",
            responses = @ApiResponse(responseCode = "204", description = "Successfully deleted the ticket"))
    public ResponseEntity<Void> deleteTicket(@PathVariable String id) {
        ticketService.deleteTicketById(id);
        return ResponseEntity.noContent().build();
    }
}