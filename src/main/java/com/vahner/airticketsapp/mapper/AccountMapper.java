package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.AccountDto;
import com.vahner.airticketsapp.entity.Account;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring")
@Component
public interface AccountMapper {
    AccountDto toDto(Account account);

    List<AccountDto> toDtoListAccount(List<Account> accounts);

    Account toEntity(AccountDto accountDto);

}