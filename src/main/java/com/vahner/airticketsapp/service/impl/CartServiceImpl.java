package com.vahner.airticketsapp.service.impl;


import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.repository.CartRepository;
import com.vahner.airticketsapp.service.interf.CartService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;

    @Override
    public void purchaseTickets(Account account) {

    }

    @Override
    public void removeFormCart(Account account, TicketDto ticketDto) {

    }

    @Override
    public List<TicketDto> getPurchaseHistory(Account account) {

        return null;
    }

    @Override
    public void addToCart(Account account, TicketDto ticketDto) {

    }


}
