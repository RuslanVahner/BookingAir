package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.dto.AccountDto;
import com.vahner.airticketsapp.dto.ShortAccountDto;
import com.vahner.airticketsapp.entity.Account;
import lombok.NonNull;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface AccountService {

    AccountDto getAccountById(String id);

    Optional<Account> getByLogin(@NonNull String login);

    List<ShortAccountDto> getAllShortAccounts();

    AccountDto create(AccountDto accountDto);

    void updateAccount(String id, AccountDto accountDto);

    void deleteAccount(String id);

    void changePassword(String id, String newPassword);

    BigDecimal getAccountBalance(String id);

}