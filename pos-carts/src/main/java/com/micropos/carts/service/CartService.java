package com.micropos.carts.service;

import com.micropos.carts.model.Cart;
import com.micropos.carts.model.CartItem;

public interface CartService {

    Cart addItemToCart(Cart cart, CartItem cartItem);
    Cart createCart(Cart cart);
    Cart getCartById(Integer cartId);
    Double getCartTotal(Integer cartId);
}
