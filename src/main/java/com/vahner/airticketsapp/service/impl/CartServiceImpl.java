package com.vahner.airticketsapp.service.impl;


import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.repository.CartRepository;
import com.vahner.airticketsapp.service.interf.CartService;
import com.vahner.airticketsapp.service.interf.TicketService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final TicketService ticketService;

    @Override
    public void purchaseTickets(Account account) {

    }

    @Override
    public void removeFormCart(Account account, TicketDto ticketDto) {

    }

    @Override
    public void addToCart(Account account, TicketDto ticketDto) {

    }
}