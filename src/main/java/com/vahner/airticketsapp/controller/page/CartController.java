package com.vahner.airticketsapp.controller.page;

import com.vahner.airticketsapp.dto.CartDto;
import com.vahner.airticketsapp.entity.Cart;
import com.vahner.airticketsapp.service.interf.CartService;
import com.vahner.airticketsapp.validation.interf.Uuid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@Validated
@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private CartService cartService;

    @GetMapping("/{uuid}")
    public ResponseEntity<CartDto> findCartById(@Uuid @PathVariable String uuid) {
        return cartService.findCartById(uuid);
    }

    @PostMapping("/createCart")
    public ResponseEntity<Cart> createCart(@RequestBody CartDto cartDto){
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(cartService.create(cartDto));
    }

    @DeleteMapping("/delete/{uuid}")
    public ResponseEntity<Void> deleteById(@PathVariable String uuid){
    cartService.deleteById(UUID.fromString(uuid));
    return ResponseEntity.noContent().build();
    }

}