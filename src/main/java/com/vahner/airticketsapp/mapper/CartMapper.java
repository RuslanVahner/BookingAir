package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.CartDto;
import com.vahner.airticketsapp.entity.Cart;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface CartMapper {

    CartDto toDto(Cart cart);

    Cart toEntity(CartDto cartDto);
}