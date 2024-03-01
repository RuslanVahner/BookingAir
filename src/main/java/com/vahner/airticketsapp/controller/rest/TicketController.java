package com.vahner.airticketsapp.controller.rest;

import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.service.interf.TicketService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequestMapping("/api/tickets")
@RequiredArgsConstructor
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/{uuid}")
    @Operation(summary = "get ticket by uuid",
            description = "Retrieves a ticket by its UUID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the ticket", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TicketDto.class))
    })
    public ResponseEntity<TicketDto> getTicketById(@PathVariable String uuid) {
        return ResponseEntity.ok(ticketService.getTicketById(uuid));
    }

    @GetMapping
    @Operation(summary = "Get All Tickets",
            description = "Retrieves a list of all tickets",
            tags = "Ticket",
            hidden = true)
    @ApiResponse(responseCode = "200", description = "Successfully returned list of tickets", content = {
            @Content(mediaType = "application/json",
                    array = @ArraySchema(schema = @Schema(implementation = TicketDto.class)))
    })
    public ResponseEntity<List<TicketDto>> getTickets() {
        List<TicketDto> ticketDtoList = ticketService.getAllTicket();
        return ResponseEntity.ok(ticketDtoList);

    }

    @PostMapping("/createTicket")
    @Operation(summary = "Create Ticket",
            description = "Creates a new ticket.")
    @ApiResponse(responseCode = "201", description = "Successfully created the ticket", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TicketDto.class))
    })
    public ResponseEntity<TicketDto> create(@Valid @RequestBody TicketDto dto) {
        return new ResponseEntity<>(ticketService.create(dto), HttpStatus.CREATED);
    }

    @PutMapping("/updateTicket/{uuid}")
    @Operation(summary = "Update Ticket",
            description = "Updates an existing ticket by its UUID")
    @ApiResponse(responseCode = "200", description = "Successfully updated the ticket", content = {
            @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TicketDto.class))
    })
    public ResponseEntity<Void> updateTicket(@PathVariable UUID uuid, @RequestBody TicketDto ticketDto) {
        ticketService.updateTicket(uuid, ticketDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteTicket/{uuid}")
    @Operation(summary = "Delete Ticket",
            description = "Deletes a ticket by its UUID.")
    @ApiResponse(responseCode = "204", description = "Successfully deleted the ticket")
    public ResponseEntity<Void> deleteTicket(@PathVariable UUID uuid) {
        ticketService.deleteTicket(uuid);
        return ResponseEntity.noContent().build();
    }
}