package com.vahner.airticketsapp.controller.rest;

import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.service.interf.TicketService;
import com.vahner.airticketsapp.validation.interf.Uuid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ticket")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public TicketDto getTicketById(@Uuid @PathVariable("uuid") String uuid) {
        return ticketService.getTicketById(uuid);
    }

    @GetMapping
    public List<TicketDto> getTickets() {
        return ticketService.getTickets();
    }

    @PostMapping("/createTicket")
    public TicketDto create(@RequestBody TicketDto ticketDto) {
        return ticketService.create(ticketDto);
    }

    @PutMapping("/updateTicket/{uuid}")
    public TicketDto updateTicket(@PathVariable UUID uuid, @RequestBody TicketDto ticketDto) {
        return ticketService.updateTicket(uuid, ticketDto);
    }

    @DeleteMapping("/deleteTicket/{uuid}")
    public void deleteTicket(@PathVariable UUID uuid) {
       ticketService.deleteTicket(uuid);
    }
}