package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.AccountDto;
import com.vahner.airticketsapp.dto.ShortAccountDto;
import com.vahner.airticketsapp.entity.Account;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountDto toDto(Account account);

    Account toEntity(AccountDto dto);

    @AfterMapping
    default void updateEntity(AccountDto accountDto, @MappingTarget Account existingAccount) {
        if (accountDto.getStatus() != null) {
            existingAccount.setStatus(accountDto.getStatus());
        }
    }

    @Mapping(target = "id", expression = "java(account.getId().toString())")
    ShortAccountDto toShortDto(Account account);

}