package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.entity.Ticket;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TicketMapper {

    @Mapping(target = "nameFlight", source = "flight.nameFlight")
    @Mapping(target = "departureDate", source = "flight.departureDate", dateFormat = "yyyy-MM-dd HH:mm")
    @Mapping(target = "arrivalDate", source = "flight.arrivalDate", dateFormat = "yyyy-MM-dd HH:mm")
    TicketDto toDto(Ticket ticket);

    @Mapping(target = "flight.nameFlight", source = "nameFlight")
    @Mapping(target = "flight.departureDate", ignore = true)
    @Mapping(target = "flight.arrivalDate", ignore = true)
    Ticket toEntity(TicketDto ticketDto);

    void updateEntityFromDto(TicketDto ticketDto, Ticket ticket);
}
