package com.micropos.carts.mapper;

import com.micropos.carts.model.Cart;
import com.micropos.carts.model.CartItem;
import com.micropos.model.CartDto;
import com.micropos.model.CartItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class CartMapperImp implements CartMapper{

    @Autowired
    ProductMapper productMapper;

    @Override
    public CartItemDto toCartItemDto(CartItem cartItem) {
        CartItemDto cartItemDto = new CartItemDto();
        cartItemDto.setId(Math.toIntExact(cartItem.getId()));
        cartItemDto.setAmount(cartItem.getAmount());
        cartItemDto.setProduct(productMapper.toProductDto(cartItem.getProduct()));
        return cartItemDto;
    }

    @Override
    public CartItem toCartItem(CartItemDto cartItemDto) {
        CartItem cartItem = new CartItem();
        cartItem.setId(cartItemDto.getId().longValue());
        cartItem.setAmount(cartItemDto.getAmount());
        cartItem.setProduct(productMapper.toProduct(cartItemDto.getProduct()));
        return cartItem;
    }

    @Override
    public Collection<CartItem> toCartItems(Collection<CartItemDto> cartItemDtos) {
        ArrayList<CartItem> cartItems = new ArrayList<>();
        for (CartItemDto cartItemDto : cartItemDtos) {
            cartItems.add(toCartItem(cartItemDto));
        }
        return cartItems;
    }

    @Override
    public Collection<CartItemDto> toCartItemsDto(Collection<CartItem> cartItems) {
        ArrayList<CartItemDto> cartItemDtos = new ArrayList<>();
        for (CartItem cartItem : cartItems) {
            cartItemDtos.add(toCartItemDto(cartItem));
        }
        return cartItemDtos;
    }

    @Override
    public Cart toCart(CartDto cartDto) {
        Cart cart = new Cart();
        cart.setId(cartDto.getId().longValue());
        cart.setCartItemList(toCartItems(cartDto.getItems()).stream().toList());
        return cart;
    }

    @Override
    public CartDto toCartDto(Cart cart) {
        CartDto cartDto = new CartDto();
        cartDto.setId(Math.toIntExact(cart.getId()));
        cartDto.setItems(toCartItemsDto(cart.getCartItemList()).stream().toList());
        return cartDto;
    }

    @Override
    public Collection<Cart> toCarts(Collection<CartDto> cartDtos) {
        ArrayList<Cart> carts = new ArrayList<>();
        for (CartDto cartDto : cartDtos) {
            carts.add(toCart(cartDto));
        }
        return carts;
    }

    @Override
    public Collection<CartDto> toCartsDto(Collection<Cart> carts) {
        ArrayList<CartDto> cartsDto = new ArrayList<>();
        for (Cart cart : carts) {
            cartsDto.add(toCartDto(cart));
        }
        return cartsDto;
    }
}
