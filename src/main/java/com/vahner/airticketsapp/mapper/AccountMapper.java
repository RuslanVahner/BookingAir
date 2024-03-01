package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.AccountDto;
import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.entity.enums.AccountStatus;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface AccountMapper {

    @Mapping(source = "status", target = "accountStatus", qualifiedByName = "accountStatus")
    AccountDto toDto(Account entity);

    @Mapping(source = "accountStatus", target = "status", qualifiedByName = "accountStatusToString")
    Account toEntity(AccountDto dto);

    void updateEntity(AccountDto accountDto, Account existingAccount);

    @Named("accountStatus")
    default AccountStatus stringToAccountStatus(String status) {
        return AccountStatus.valueOf(status.toUpperCase());
    }

    @Named("accountStatusToString")
    default String accountStatusToString(AccountStatus status) {
        return status.name();
    }

}