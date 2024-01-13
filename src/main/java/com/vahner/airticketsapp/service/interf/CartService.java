package com.vahner.airticketsapp.service.interf;

import com.vahner.airticketsapp.entity.Cart;

public interface CartService {
    Cart getByCartId(String id);
}
