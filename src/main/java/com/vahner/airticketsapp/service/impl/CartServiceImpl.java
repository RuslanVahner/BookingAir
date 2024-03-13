package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.entity.Cart;
import com.vahner.airticketsapp.entity.Ticket;
import com.vahner.airticketsapp.entity.enums.TicketStatus;
import com.vahner.airticketsapp.exception.InsufficientFundsException;
import com.vahner.airticketsapp.repository.CartRepository;
import com.vahner.airticketsapp.service.interf.CartService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private CartRepository cartRepository;

    @Transactional
    public void purchaseTickets(UUID accountId) {
        Cart cart = cartRepository.findByAccountId(accountId)
                .orElseThrow(() -> new IllegalArgumentException("Cart not found for the account id: " + accountId));

        Account account = cart.getAccount();
        BigDecimal totalCost = calculateTotalCost(cart.getTickets());

        if (account.getBalance().compareTo(totalCost) < 0) {
            throw new InsufficientFundsException("Insufficient funds on the balance to complete the purchase");
        }

        account.setBalance(account.getBalance().subtract(totalCost));

        cart.getTickets().forEach(ticket -> {
            ticket.setTicketStatus(TicketStatus.PAID);

        });

        cart.getTickets().clear();
        cartRepository.save(cart);
    }

    private BigDecimal calculateTotalCost(List<Ticket> tickets) {
        return tickets.stream()
                .map(Ticket::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}