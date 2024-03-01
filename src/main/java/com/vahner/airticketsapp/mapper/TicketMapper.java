package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.entity.Ticket;
import com.vahner.airticketsapp.entity.Trips;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", uses = {Trips.class})
@Component
public interface TicketMapper {

    @Mapping(source = "trip", target = "tripDto")
    TicketDto toDto(Ticket entity);

    @Mapping(source = "tripDto", target = "trip")
    Ticket toEntity(TicketDto dto);

    void updateEntity(TicketDto ticketDto, Ticket existingTicket);
}
