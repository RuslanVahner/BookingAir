package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.entity.Ticket;
import com.vahner.airticketsapp.entity.Trips;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", uses = {Account.class, Trips.class})
@Component
public interface TicketMapper {

    TicketDto toDtoTicket(Ticket ticket);

    Ticket toTicketEntity(TicketDto ticketDto);

    List<TicketDto> toDtoTicketList(List<Ticket> tickets);
}
