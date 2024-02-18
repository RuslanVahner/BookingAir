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

    /**
     * Getting information about his UUID account
     * Получение информацию об аккаунте по его UUID.
     *
     * @param uuid аккаунта.
     * @return DTO с информацией об аккаунте.
     */
    @Override
    public AccountDto getAccountById(String uuid) {
        Account account = accountRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return accountMapper.toDto(account);
    }

    /**
     * Getting lists of all accounts.
     * Получение списоков всех аккаунтов.
     *
     * @return Список DTO с информацией об аккаунтах.
     */
    @Override
    public List<AccountDto> getAccounts() {
        return accountMapper.toDtoListAccount(accountRepository.findAll());
    }

    /**
     * Creates a new account with the specified data.
     * Создает новый аккаунт с указанными данными.
     *
     * @param accountDto с данными для создания аккаунта.
     * @return DTO с информацией о созданном аккаунте.
     */
    @Override
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public AccountDto create(AccountDto accountDto) {
        log.debug("Account create");
        Account accountToCreate = accountMapper.toEntity(accountDto);
        accountToCreate.setBalance(BigDecimal.ZERO);
        Account createdAccount = accountRepository.save(accountToCreate);
        return accountMapper.toDto(createdAccount);
    }

    /**
     * Update the account information with the specified UUID.
     * Обновление информации об аккаунте с указанным UUID.
     *
     * @param uuid аккаунта для обновления.
     * @param accountDto с новыми данными для аккаунта.
     * @return DTO с обновленной информацией об аккаунте.
     */
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
     * Method for changing the user password.
     * Метод для изменения пароля пользователя.
     *
     * @param uuid аккаунта, для которого меняется пароль.
     * @param newPassword новый пароль.
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void changePassword(String uuid, String newPassword) {
        Account account = accountRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        account.setPassword(newPassword);
        accountRepository.save(account);
    }

    /**
     * Delete the account with the specified UUID.
     * Удаление аккаунта с указанным UUID.
     *
     * @param uuid аккаунта для удаления.
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteAccount(String uuid) {
        Account account = accountRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        accountRepository.delete(account);
    }

    /**
     * Getting the current account balance by its UUID.
     * Получение текущего баланса аккаунта по его UUID.
     *
     * @param uuid аккаунта.
     * @return Текущий баланс аккаунта.
     */
    @Override
    public BigDecimal getAccountBalance(String uuid) {
        Account account = accountRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        return account.getBalance();
    }

    /**
     * Add tickets to the cart and calculate the total cost if tickets are more than one.
     * Добавление билетов в корзину и рассчет общей стоимости, если билетов больше одного.
     *
     * @param uuid аккаунта, для которого добавляется билет в корзину.
     * @param ticketDto с данными о билете.
     */
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

    /**
     * Removing the ticket from the cart.
     * Удаление билета из корзины.
     *
     * @param uuid аккаунта, из которого удаляется билет.
     * @param ticketDto с данными о билете для его удаления.
     */
    @Override
    public void removeFormCart(String uuid, TicketDto ticketDto) {
        log.debug("Returning a ticket from a shopping cart");
        Account account = accountRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        cartService.removeFormCart(account, ticketDto);
    }

    /**
     * Buying tickets for his account via his UUID.
     * Покупка билетов для аккаунта по его UUID.
     *
     * @param uuid аккаунта для покупки билетов.
     */
    @Override
    public void purchaseTickets(String uuid) {
        log.debug("Tickets purchased");
        Account account = accountRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        cartService.purchaseTickets(account);
    }

    /**
     * View ticket purchase history for a specific account.
     * Просмотр истории покупок билетов для конкретного аккаунта.
     *
     * @param uuid аккаунта.
     * @return Список TicketDto, представляющий историю покупок.
     * @throws AccountNotFoundException если аккаунт с указанным UUID не найден.
     */
    @Override
    public List<TicketDto> getPurchaseHistory(String uuid) {
        Account account = accountRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));

        return cartService.getPurchaseHistory(account);
    }
}