package com.vahner.airticketsapp.service.impl;

import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.repository.AccountRepository;
import com.vahner.airticketsapp.service.interf.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public Account getAccountById(UUID id)   {
        return accountRepository.getReferenceById(id);
    }
    @Override
    public List<Account> getAccount(){
        return accountRepository.findAll();
    }

    @Override
    public Account create(Account account) {
        return accountRepository.save(account);
    }
}
