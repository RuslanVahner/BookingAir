package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.AccountDto;
import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.entity.Cart;
import com.vahner.airticketsapp.entity.Passenger;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "spring", uses = {Passenger.class, Cart.class})
@Component
public interface AccountMapper {
    AccountDto toDto(Account account);

    List<AccountDto> toDtoListAccount(List<Account> accounts);

    Account toEntity(AccountDto accountDto);

}