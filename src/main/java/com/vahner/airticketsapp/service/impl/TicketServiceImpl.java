package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.entity.Cart;
import com.vahner.airticketsapp.entity.Ticket;
import com.vahner.airticketsapp.exception.TicketNotFoundException;
import com.vahner.airticketsapp.generator.QRCodeGenerator;
import com.vahner.airticketsapp.mapper.TicketMapper;
import com.vahner.airticketsapp.repository.TicketRepository;
import com.vahner.airticketsapp.service.interf.TicketService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketServiceImpl  implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;
    private final QRCodeGenerator qrCodeGenerator;

    @Override
    public TicketDto getTicketById(String id) {
        log.info("Retrieving ticket by ID: {}", id);
        Ticket ticket = ticketRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new TicketNotFoundException("Ticket with ID " + id + " not found"));
        return ticketMapper.toDto(ticket);
    }

    @Override
    public List<TicketDto> getAllTickets() {
        log.info("Retrieving all tickets");
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(ticketMapper::toDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TicketDto createTicket(TicketDto ticketDto) {
        log.info("Creating new ticket");
        Ticket ticket = ticketMapper.toEntity(ticketDto);
        ticket = ticketRepository.save(ticket);
        return ticketMapper.toDto(ticket);
    }

    @Override
    @Transactional
    public void updateTicket(String id, TicketDto ticketDto) {
        log.info("Updating ticket with ID: {}", id);
        Ticket ticket = ticketRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new TicketNotFoundException("Ticket with ID " + id + " not found"));
        ticketMapper.updateEntityFromDto(ticketDto, ticket);
        ticket = ticketRepository.save(ticket);
        ticketMapper.toDto(ticket);
    }

    @Override
    @Transactional
    public void deleteTicketById(String id) {
        log.info("Deleting ticket with ID: {}", id);
        Ticket ticket = ticketRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new TicketNotFoundException("Ticket with ID " + id + " not found"));
        ticketRepository.delete(ticket);
    }
}
