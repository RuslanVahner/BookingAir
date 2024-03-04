package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.AccountDto;
import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.entity.enums.AccountStatus;
import com.vahner.airticketsapp.exception.AccountNotFoundException;
import com.vahner.airticketsapp.mapper.AccountMapper;
import com.vahner.airticketsapp.repository.AccountRepository;
import com.vahner.airticketsapp.service.interf.AccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    /**
     * Getting information about his UUID account
     * Получение информацию об аккаунте по его UUID.
     *
     * @param uuid аккаунта.
     * @return DTO с информацией об аккаунте.
     */
    @Override
    public AccountDto getAccountByUUID(String uuid) {
        log.info("Getting account by UUID: {}", uuid);
        Account account = accountRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new AccountNotFoundException("Account with id " + uuid + " not found"));
        return accountMapper.toDto(account);
    }

    /**
     * Getting lists of all accounts.
     * Получение списоков всех аккаунтов.
     *
     * @return Список DTO с информацией об аккаунтах.
     */
    @Override
    public List<AccountDto> getAllAccounts() {
        log.info("Getting all accounts");
        List<Account> accounts = accountRepository.findAll();
        return accounts.stream().map(accountMapper::toDto).collect(Collectors.toList());
    }

    /**
     * Creates a new account with the specified data.
     * Создает новый аккаунт с указанными данными.
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
        Account accountSave = accountRepository.save(account);

        return accountMapper.toDto(accountSave);
    }

    /**
     * Update the account information with the specified UUID.
     * Обновление информации об аккаунте с указанным UUID.
     *
     * @param uuid аккаунта для обновления.
     * @param accountDto с новыми данными для аккаунта.
     */
    @Override
    @Transactional(isolation = Isolation.READ_COMMITTED)
    public void updateAccount(String uuid, AccountDto accountDto) {
        log.info("Updating account with UUID {}: {}", uuid, accountDto);
        Account existingAccount = accountRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new AccountNotFoundException("Account with id" + uuid + " not found"));

        accountMapper.updateEntity(accountDto,existingAccount);
        accountRepository.save(existingAccount);

    }

    /**
     * Method for changing the user password.
     * Метод для изменения пароля пользователя.
     *
     * @param uuid аккаунта, для которого меняется пароль.
     * @param newPassword новый пароль.
     */
    @Override
    @Transactional
    public void changePassword(String uuid, String newPassword) {
        Account account = accountRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new AccountNotFoundException("Account with id" + uuid + " not found"));

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
                .orElseThrow(() -> new AccountNotFoundException("Account with id" + uuid + " not found"));
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
                .orElseThrow(() -> new AccountNotFoundException("Account with id" + uuid + " not found"));

        return account.getBalance();
    }
}