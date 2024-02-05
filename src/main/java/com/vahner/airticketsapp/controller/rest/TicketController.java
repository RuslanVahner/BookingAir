package com.vahner.airticketsapp.controller.rest;

import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.service.interf.TicketService;
import com.vahner.airticketsapp.validation.interf.Uuid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/ticket")
public class TicketController {

    private final TicketService ticketService;

    @GetMapping("/{uuid}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TicketDto> getTicketById(@Uuid @PathVariable("uuid") String uuid){
        TicketDto ticketDto = ticketService.getTicketById(uuid);
        return ResponseEntity.ok(ticketDto);
    }

    @GetMapping
    public ResponseEntity<List<TicketDto>> getTickets(){
        List<TicketDto> ticketDtoList = ticketService.getTickets();
        return ResponseEntity.ok(ticketDtoList);
    }

    @PostMapping("/createTicket")
    public ResponseEntity<TicketDto> create (@RequestBody TicketDto ticketDto){
        TicketDto createTicketDto = ticketService.create(ticketDto);
        return ResponseEntity.ok(createTicketDto);
    }

    @PutMapping("/updateTicket/{uuid}")
    public ResponseEntity<TicketDto> updateTicket(@PathVariable UUID uuid, @RequestBody  TicketDto ticketDto){
        TicketDto updateTicketDto = ticketService.updateTicket(uuid,ticketDto);
        return ResponseEntity.ok(updateTicketDto);
    }

    @DeleteMapping("/deleteTicket/{uuid}")
    public ResponseEntity<String> deleteTicket(@PathVariable UUID uuid){
        ticketService.deleteTicket(uuid);
        return new ResponseEntity<>("Ticket deleted successfully", HttpStatus.OK);
    }

}