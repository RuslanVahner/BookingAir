package com.vahner.airticketsapp.dto;

import com.vahner.airticketsapp.entity.enums.AccountStatus;
import lombok.*;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class AccountDto {

     String uuid;
     String login;
     String owner;
     AccountStatus status;
     BigDecimal balance;

}