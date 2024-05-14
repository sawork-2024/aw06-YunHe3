package com.micropos.carts.mapper;

import com.micropos.carts.model.Cart;
import com.micropos.carts.model.CartItem;
import com.micropos.model.CartDto;
import com.micropos.model.CartItemDto;

import java.util.Collection;

public interface CartMapper {

    CartItemDto toCartItemDto(CartItem cartItem);
    CartItem toCartItem(CartItemDto cartItemDto);
    Collection<CartItem> toCartItems(Collection<CartItemDto> cartItemDtos);
    Collection<CartItemDto> toCartItemsDto(Collection<CartItem> cartItems);
    Cart toCart(CartDto cartDto);
    CartDto toCartDto(Cart cart);
    Collection<Cart> toCarts(Collection<CartDto> cartDtos);
    Collection<CartDto> toCartsDto(Collection<Cart> carts);
}
