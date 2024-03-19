package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.entity.Flight;
import com.vahner.airticketsapp.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring", uses = Flight.class)
public interface TicketMapper {

    TicketDto toDto(Ticket ticket);

    Ticket toEntity(TicketDto ticketDto);

    void updateEntityFromDto(TicketDto ticketDto, @MappingTarget Ticket ticket);
}
