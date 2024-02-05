package com.vahner.airticketsapp.service.interf;


import com.vahner.airticketsapp.dto.AccountDto;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    AccountDto getAccountById(String uuid);
    AccountDto create(AccountDto accountDto);
    AccountDto updateAccount(UUID uuid, AccountDto accountDto);
    void deleteAccount(UUID uuid);
    List<AccountDto> getAccounts();
}
