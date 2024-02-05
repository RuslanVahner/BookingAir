package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.dto.AccountDto;
import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.exception.AccountNotFoundException;
import com.vahner.airticketsapp.mapper.AccountMapper;
import com.vahner.airticketsapp.repository.AccountRepository;
import com.vahner.airticketsapp.service.interf.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    @Override
    public AccountDto getAccountById(String uuid) {
        Account account = accountRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        return accountMapper.toDto(account);

    }

    @Override
    public List<AccountDto> getAccounts(){
        return accountMapper.toDtoListAccount(accountRepository.findAll());
    }

    @Override
    public AccountDto create(AccountDto accountDto) {
        Account accountToCreate = accountMapper.toEntity(accountDto);
        accountToCreate.setBalance(BigDecimal.ZERO);
        Account createdAccount = accountRepository.save(accountToCreate);
        return accountMapper.toDto(createdAccount);
    }

    @Override
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

    @Override
    public void deleteAccount(UUID uuid) {
             Account account = accountRepository.findById(uuid)
                .orElseThrow(() -> new AccountNotFoundException("Account not found"));
        accountRepository.delete(account);
    }
}