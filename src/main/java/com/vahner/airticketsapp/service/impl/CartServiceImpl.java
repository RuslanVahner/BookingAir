package com.vahner.airticketsapp.service.impl;


import com.vahner.airticketsapp.dto.CartDto;
import com.vahner.airticketsapp.entity.Cart;
import com.vahner.airticketsapp.exception.CartNotFoundException;
import com.vahner.airticketsapp.mapper.CartMapper;
import com.vahner.airticketsapp.repository.CartRepository;
import com.vahner.airticketsapp.service.interf.CartService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    @Override
    public ResponseEntity<CartDto> findCartById(String uuid) {
        Cart cart = cartRepository.findById(UUID.fromString(uuid))
                .orElseThrow(() -> new CartNotFoundException("Cart not found with id: " + uuid));
        CartDto cartDto = cartMapper.toDto(cart);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cartDto);
    }

    @Override
    public Cart create(CartDto cartDto) {
        Cart cart = cartMapper.toEntity(cartDto);
        return cartRepository.save(cart);
    }

    @Override
    public void deleteById(UUID uuid) {
        cartRepository.deleteById(uuid);
    }

}