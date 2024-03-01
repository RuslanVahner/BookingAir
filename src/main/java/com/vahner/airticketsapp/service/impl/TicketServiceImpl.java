package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.entity.Ticket;
import com.vahner.airticketsapp.exception.TicketNotFoundException;
import com.vahner.airticketsapp.mapper.TicketMapper;
import com.vahner.airticketsapp.repository.TicketRepository;
import com.vahner.airticketsapp.service.interf.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TicketServiceImpl implements TicketService {

    private final TicketRepository ticketRepository;
    private final TicketMapper ticketMapper;

    /**
     * Получение информации о билете по его UUID.
     *
     * @param uuid билета.
     * @return DTO с информацией о билете.
     */
    @Override
    public TicketDto getTicketById(String uuid) {
        log.info("Getting ticket by UUID: {}", uuid);
        Ticket ticket = ticketRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new TicketNotFoundException("Ticket with id " + uuid + " not found"));

        return ticketMapper.toDto(ticket);
    }

    /**
     * Получение списка всех билетов.
     *
     * @return Список DTO с информацией о билетах.
     */

    @Override
    public List<TicketDto> getAllTicket() {
        log.info("Getting all tickets");
        List<Ticket> tickets = ticketRepository.findAll();
        return tickets.stream().map(ticketMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Создание нового билета с указанными данными.
     *
     * @param ticketDto для создания билета.
     * @return DTO с информацией о созданном билете.
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public TicketDto create(TicketDto ticketDto) {
        log.info("Creating ticket: {}", ticketDto);
        Ticket ticket = ticketMapper.toEntity(ticketDto);
        Ticket ticketSave = ticketRepository.save(ticket);
        return ticketMapper.toDto(ticketSave);
    }

    /**
     * Обновление информации о билете с указанным UUID.
     *
     * @param uuid      билета для обновления.
     * @param ticketDto новые данные для билета.
     */
    public void updateTicket(UUID uuid, TicketDto ticketDto) {
        Ticket ticket = ticketRepository.findById(uuid)
                .orElseThrow(() -> new TicketNotFoundException("Ticket with id " + uuid + " not found"));
        ticketMapper.updateEntity(ticketDto, ticket);
        ticketRepository.save(ticket);
    }

    /**
     * Удаление билета с указанным UUID.
     *
     * @param uuid билета для удаления.
     */
    @Override
    public void deleteTicket(UUID uuid) {
        ticketRepository.deleteById(uuid);
    }

}