package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.AccountDto;
import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.exception.AccountNotFoundException;
import com.vahner.airticketsapp.mapper.AccountMapper;
import com.vahner.airticketsapp.repository.AccountRepository;
import com.vahner.airticketsapp.service.interf.AccountService;
import com.vahner.airticketsapp.service.interf.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final CartService cartService;

    @Override
    public AccountDto getAccountById(String uuid) {
        Account account = accountRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return accountMapper.toDto(account);
    }

    @Override
    public List<AccountDto> getAccounts() {
        return accountMapper.toDtoListAccount(accountRepository.findAll());
    }

    @Override
    @Transactional
    public AccountDto create(AccountDto accountDto) {
        log.debug("Account create");
        Account accountToCreate = accountMapper.toEntity(accountDto);
        accountToCreate.setBalance(BigDecimal.ZERO);
        Account createdAccount = accountRepository.save(accountToCreate);
        return accountMapper.toDto(createdAccount);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public AccountDto updateAccount(UUID uuid, AccountDto accountDto) {
        Account existingAccount = accountRepository.findById(uuid)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        existingAccount.setLogin(accountDto.getLogin());
        existingAccount.setPassword(accountDto.getPassword());
        existingAccount.setOwner(accountDto.getOwner());
        existingAccount.setBalance(accountDto.getBalance());
        existingAccount.setStatus(accountDto.getStatus());

        Account updatedAccount = accountRepository.save(existingAccount);
        return accountMapper.toDto(updatedAccount);
    }

    /**
     * A method for changing a user's password;
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void changePassword(String uuid, String newPassword) {
        Account account = accountRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        account.setPassword(newPassword);
        accountRepository.save(account);
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteAccount(UUID uuid) {
        Account account = accountRepository.findById(uuid)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        accountRepository.delete(account);
    }

    /**
     *A method for obtaining information about a passenger balance;
     */
    @Override
    @Transactional
    public BigDecimal getAccountBalance(String uuid) {
        Account account = accountRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        return account.getBalance();
    }

    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void purchaseTickets(Account account) {
        cartService.purchaseTickets(account);
    }

    @Override
    public void addToCart(String uuid, TicketDto ticketDto) {
        log.debug("Ticket add to the cart");
        Account account = accountRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        BigDecimal currentBalance = account.getBalance();
        BigDecimal ticketPrice = ticketDto.getPrice();
        BigDecimal sumBalance = currentBalance.add(ticketPrice);
        account.setBalance(sumBalance);
        accountRepository.save(account);
        cartService.addToCart(account, ticketDto);
    }

    @Override
    public void removeFormCart(String uuid, TicketDto ticketDto) {
        log.debug("Returning a ticket from a shopping cart");
        Account account = accountRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        cartService.removeFormCart(account, ticketDto);
    }

    @Override
    public void purchaseTickets(String uuid) {
        log.debug("Tickets purchased");
        Account account = accountRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        cartService.purchaseTickets(account);

    }
}