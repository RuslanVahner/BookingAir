package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.entity.Account;

public interface CartService {
    void addToCart(Account account, TicketDto ticketDto);
    void purchaseTickets(Account account);
    void removeFormCart(Account account, TicketDto ticketDto);
}