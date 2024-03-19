package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.AccountDto;
import com.vahner.airticketsapp.dto.CartDto;
import com.vahner.airticketsapp.dto.ShortAccountDto;
import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.entity.Cart;
import com.vahner.airticketsapp.exception.AccountNotFoundException;
import com.vahner.airticketsapp.exception.CartNotFoundException;
import com.vahner.airticketsapp.exception.ErrorMessage;
import com.vahner.airticketsapp.mapper.AccountMapper;
import com.vahner.airticketsapp.mapper.CartMapper;
import com.vahner.airticketsapp.repository.AccountRepository;
import com.vahner.airticketsapp.repository.CartRepository;
import com.vahner.airticketsapp.service.interf.AccountService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final CartRepository cartRepository;
    private final AccountMapper accountMapper;
    private final CartMapper cartMapper;

    /**
     * Getting information about his ID account
     * Получение информацию об аккаунте по его ID.
     *
     * @param id аккаунта.
     * @return DTO с информацией об аккаунте.
     */
    public AccountDto getAccountWithCartById(String id) {
        log.info("Getting account with cart by ID: {}", id);
        Account account = accountRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new AccountNotFoundException(String.format(ErrorMessage.M_ACCOUNT_NOT_FOUND, id)));
        Cart cart = cartRepository.findByAccountId(account.getId())
                .orElseThrow(() -> new CartNotFoundException(String.format(ErrorMessage.M_CART_NOT_FOUND, id)));
        CartDto cartDto = cartMapper.toDto(cart);
        AccountDto accountDto = accountMapper.toDto(account);
        accountDto.setCartDto(cartDto);
        return accountDto;
    }

    @Override
    public Optional<Account> getByLogin(@NonNull String login) {
        return accountRepository.findByLogin(login);
    }

    /**
     * Getting lists of all accounts.
     * Получение списоков всех аккаунтов.
     *
     * @return Список DTO с информацией об аккаунтах.
     */
    @Override
    public List<ShortAccountDto> getAllShortAccounts() {
        log.info("Retrieving all short account details");
        return accountRepository.findAll().stream()
                .map(accountMapper::toShortDto)
                .collect(Collectors.toList());
    }

    /**
     * Создает новый аккаунт с указанными данными и инициализирует для него корзину.
     *
     * @param accountDto с данными для создания аккаунта.
     * @return DTO с информацией о созданном аккаунте.
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public AccountDto create(AccountDto accountDto) {
        log.info("Creating account: {}", accountDto);
        Account account = accountMapper.toEntity(accountDto);
        account.setBalance(BigDecimal.ZERO);
        account = accountRepository.save(account);

        Cart cart = new Cart();
        cart.setAccount(account);
        cartRepository.save(cart);

        return accountMapper.toDto(account);
    }

    /**
     * Update the account information with the specified ID.
     * Обновление информации об аккаунте с указанным ID.
     *
     * @param id         - аккаунта для обновления.
     * @param accountDto с новыми данными для аккаунта.
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void updateAccount(String id, AccountDto accountDto) {
        log.info("Updating account with ID {}: {}", id, accountDto);
        Account existingAccount = accountRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new AccountNotFoundException(String.format(ErrorMessage.M_ACCOUNT_NOT_FOUND, id)));
        accountMapper.updateEntity(accountDto, existingAccount);
        accountRepository.save(existingAccount);
    }

    /**
     * Delete the account with the specified ID.
     * Удаление аккаунта с указанным ID.
     *
     * @param id аккаунта для удаления.
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void deleteAccount(String id) {
        Account account = accountRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new AccountNotFoundException(String.format(ErrorMessage.M_ACCOUNT_NOT_FOUND, id)));
        accountRepository.delete(account);
    }

    /**
     * Method for changing the user password.
     * Метод для изменения пароля пользователя.
     *
     * @param id          - аккаунта, для которого меняется пароль.
     * @param newPassword новый пароль.
     */
    @Override
    public void changePassword(String id, String newPassword) {
        log.info("Changing password for account ID: {}", id);
        Account account = accountRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new AccountNotFoundException(String.format(ErrorMessage.M_ACCOUNT_NOT_FOUND, id)));

        account.setPassword(newPassword);
        accountRepository.save(account);
    }
    /**
     * Getting the current account balance by its ID.
     * Получение текущего баланса аккаунта по его ID.
     *
     * @param id аккаунта.
     * @return Текущий баланс аккаунта.
     */
    @Override
    public BigDecimal getAccountBalance(String id) {
        log.info("Retrieving account balance for UUID: {}", id);
        Account account = accountRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new AccountNotFoundException(String.format(ErrorMessage.M_ACCOUNT_NOT_FOUND, id)));

        return account.getBalance();
    }
}