package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.entity.Ticket;
import com.vahner.airticketsapp.exception.TicketNotFoundException;
import com.vahner.airticketsapp.mapper.TicketMapper;
import com.vahner.airticketsapp.repository.TicketRepository;
import com.vahner.airticketsapp.service.interf.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    @Override
    public TicketDto getTicketById(String uuid) {
        Ticket ticket = ticketRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));
        return ticketMapper.toDtoTicket(ticket);
    }

    @Override
    @Transactional
    public TicketDto create(TicketDto ticketDto) {
        Ticket ticket = ticketMapper.toTicketEntity(ticketDto);
        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDtoTicket(savedTicket);
    }

    @Override
    public List<TicketDto> getTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return ticketMapper.toDtoTicketList(tickets);
    }

    @Override
    @Transactional
    public TicketDto updateTicket(UUID uuid, TicketDto ticketDto) {
        Ticket existingTicket = ticketRepository.findById(uuid)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        existingTicket.setPrice(ticketDto.getPrice());
        existingTicket.setData(ticketDto.getData());

        Ticket updatedTicket = ticketRepository.save(existingTicket);
        return ticketMapper.toDtoTicket(updatedTicket);
    }

    @Override
    public void deleteTicket(UUID uuid) {
        ticketRepository.deleteById(uuid);
    }

    @Override
    public void cancelTickets(List<Ticket> tickets) {

    }

}