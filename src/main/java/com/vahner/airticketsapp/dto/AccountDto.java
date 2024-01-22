package com.vahner.airticketsapp.dto;

import com.vahner.airticketsapp.entity.enums.AccountStatus;
import lombok.Value;

import java.math.BigDecimal;
import java.util.UUID;

@Value
public class AccountDto {
    UUID id;
    String login;
    String password;
    BigDecimal balance;
    AccountStatus status;
}
