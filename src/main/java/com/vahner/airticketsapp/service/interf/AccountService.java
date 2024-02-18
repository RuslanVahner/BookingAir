package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.dto.AccountDto;
import com.vahner.airticketsapp.dto.TicketDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountDto getAccountById(String uuid);

    AccountDto create(AccountDto accountDto);

    AccountDto updateAccount(UUID uuid, AccountDto accountDto);

    void deleteAccount(String uuid);

    List<AccountDto> getAccounts();

    void changePassword(String uuid, String newPassword);

    BigDecimal getAccountBalance(String uuid);

    void addToCart(String uuid, TicketDto ticketDto);

    void removeFormCart(String uuid, TicketDto ticketDto);

    void purchaseTickets(String uuid);

    List<TicketDto> getPurchaseHistory(String uuid);
}

