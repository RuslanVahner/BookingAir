package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.entity.Ticket;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.UUID;

@Service
public interface TicketService {
    TicketDto getTicketById(String uuid);
    TicketDto create(TicketDto ticketDto);
    List<TicketDto> getTickets();
    TicketDto updateTicket(UUID uuid, TicketDto ticketDto);
    void deleteTicket(UUID uuid);

    void cancelTickets(List<Ticket> tickets);
}
