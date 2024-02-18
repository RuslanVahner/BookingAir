package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.entity.Account;

import java.util.List;

public interface CartService {
    void addToCart(Account account, TicketDto ticketDto);

    void purchaseTickets(Account account);

    void removeFormCart(Account account, TicketDto ticketDto);

    List<TicketDto> getPurchaseHistory(Account account);
}