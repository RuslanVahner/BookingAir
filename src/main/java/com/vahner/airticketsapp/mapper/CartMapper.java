package com.vahner.airticketsapp.mapper;

import com.vahner.airticketsapp.entity.Cart;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper(componentModel = "Spring")
@Component
public interface CartMapper {

   CartDto toDtoCart(Cart cart);
   List<CartDto> toDtoCartList(List<Cart> carts);
}
