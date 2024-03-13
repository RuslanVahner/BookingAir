package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.dto.TicketDto;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

public interface TicketService {

    TicketDto getTicketById(String id);

    void updateTicket(String id, TicketDto ticketDto);

    List<TicketDto> getAllTickets();

    TicketDto createTicket(TicketDto ticketDto);

    void deleteTicketById(String id);

}