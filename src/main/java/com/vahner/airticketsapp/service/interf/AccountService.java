package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.dto.AccountDto;
import com.vahner.airticketsapp.dto.ShortAccountDto;
import com.vahner.airticketsapp.entity.Account;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    AccountDto getAccountWithCartById(String id);

    List<ShortAccountDto> getAllShortAccounts();

    void updateAccount(String id, AccountDto accountDto);

    void deleteAccount(String id);

    void changePassword(String id, String newPassword);

    BigDecimal getAccountBalance(String id);

    void save(Account account);

    boolean existsByLogin(String login);
}