package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.entity.Account;
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

        Ticket ticket = ticketRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        return ticketMapper.toDtoTicket(ticket);
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

        Ticket ticket = ticketMapper.toTicketEntity(ticketDto);
        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDtoTicket(savedTicket);
    }

    /**
     * Получение списка всех билетов.
     *
     * @return Список DTO с информацией о билетах.
     */
    @Override
    public List<TicketDto> getTickets() {
        List<Ticket> tickets = ticketRepository.findAll();
        return ticketMapper.toDtoTicketList(tickets);
    }

    /**
     * Обновление информации о билете с указанным UUID.
     *
     * @param uuid билета для обновления.
     * @param ticketDto новые данные для билета.
     * @return DTO с обновленной информацией о билете.
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public TicketDto updateTicket(UUID uuid, TicketDto ticketDto) {

        Ticket existingTicket = ticketRepository.findById(uuid)
                .orElseThrow(() -> new TicketNotFoundException("Ticket not found"));

        existingTicket.setPrice(ticketDto.getPrice());
        existingTicket.setData(ticketDto.getData());

        Ticket updatedTicket = ticketRepository.save(existingTicket);
        return ticketMapper.toDtoTicket(updatedTicket);
    }

    /**
     * Удаление билета с указанным UUID.
     *
     * @param uuid билета для удаления.
     */
    @Override
    @Transactional(isolation = Isolation.REPEATABLE_READ)
    public void deleteTicket(UUID uuid) {

        ticketRepository.deleteById(uuid);
    }

    /**
     * Отмена списка билетов.
     *
     * @param tickets Список билетов для отмены.
     */
    @Override
    public void cancelTickets(List<Ticket> tickets) {

        ticketRepository.deleteAll(tickets);
    }

    /**
     * Получение списка билетов для указанного аккаунта.
     *
     * @param account Аккаунт, для которого нужно получить билеты.
     * @return Список DTO с информацией о билетах для аккаунта.
     */
    @Override
    public List<TicketDto> getAccountTickets(Account account) {
        log.debug("Fetching tickets for account with UUID: {}", account.getId());

        List<Ticket> accountTickets = ticketRepository.findByAccount(account);
        return ticketMapper.toDtoTicketList(accountTickets);
    }
}