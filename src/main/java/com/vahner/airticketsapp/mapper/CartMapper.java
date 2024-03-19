package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.dto.CartDto;
import com.vahner.airticketsapp.entity.Cart;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CartMapper {

    CartDto toDto(Cart cart);
}
