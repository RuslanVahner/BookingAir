package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.dto.AccountDto;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    List<AccountDto> getAllAccounts();

    AccountDto create(AccountDto accountDto);

    void updateAccount(String uuid, AccountDto accountDto);

    void deleteAccount(String uuid);

    AccountDto getAccountByUUID(String uuid);

    void changePassword(String uuid, String newPassword);

    BigDecimal getAccountBalance(String uuid);

}