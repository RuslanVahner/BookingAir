package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.dto.CartDto;
import com.vahner.airticketsapp.dto.TicketDto;
import com.vahner.airticketsapp.entity.Account;
import com.vahner.airticketsapp.entity.Cart;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CartService {

    ResponseEntity<CartDto> findCartById(String uuid);

    Cart create(CartDto cartDto);

    void deleteById(UUID uuid);
}