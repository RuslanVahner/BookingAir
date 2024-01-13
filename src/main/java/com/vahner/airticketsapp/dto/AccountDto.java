package com.vahner.airticketsapp.dto;

import com.vahner.airticketsapp.entity.enums.AccountStatus;
import lombok.Value;

import java.math.BigDecimal;

@Value
public class AccountDto {
    String id;
    String login;
    String password;
    BigDecimal balance;
    AccountStatus status;
}
