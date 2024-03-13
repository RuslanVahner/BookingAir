package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.AccountDto;
import com.vahner.airticketsapp.dto.ShortAccountDto;
import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.entity.enums.AccountStatus;
import com.vahner.airticketsapp.entity.enums.Role;
import org.mapstruct.*;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AccountMapper {

    @Mapping(source = "status", target = "accountStatus", qualifiedByName = "accountStatus")
    AccountDto toDto(Account entity);

    @Mapping(source = "accountStatus", target = "status", qualifiedByName = "accountStatusToString")
    Account toEntity(AccountDto dto);

    @AfterMapping
    default void updateEntity(AccountDto accountDto, @MappingTarget Account existingAccount){
        if(accountDto.getStatus() != null){
            existingAccount.setStatus(accountDto.getStatus());
        }
    }

    @Mapping(target = "id", expression = "java(account.getId().toString())")
    ShortAccountDto toShortDto(Account account);

    @Named("accountStatus")
    default AccountStatus stringToAccountStatus(String status) {
        return AccountStatus.valueOf(status.toUpperCase());
    }

    @Named("accountStatusToString")
    default String accountStatusToString(AccountStatus status) {
        return status.name();
    }

    @Named("stringToRole")
    default Role stringToRole(String role) {
        return Role.valueOf(role.toUpperCase());
    }

    @Named("roleToString")
    default String roleToString(Role role) {
        return role.name();
    }
}