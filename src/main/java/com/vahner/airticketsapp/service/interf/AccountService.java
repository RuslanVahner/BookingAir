package com.vahner.airticketsapp.service.interf;


import com.vahner.airticketsapp.entity.Account;

import java.util.List;
import java.util.UUID;

public interface AccountService {
    Account getAccountById(UUID id);
    List<Account> getAccount();
    Account create(Account account);
}
