package com.micropos.carts.rest;

import com.micropos.api.CartsApi;
import com.micropos.carts.model.Cart;
import com.micropos.carts.model.CartItem;
import com.micropos.carts.service.CartService;
import com.micropos.model.CartDto;
import com.micropos.model.CartItemDto;
import com.micropos.carts.mapper.CartMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CartController implements CartsApi {

    private CartMapper cartMapper;
    private CartService cartService;

    @Autowired
    public void setCartMapper(CartMapper cartMapper) { this.cartMapper = cartMapper; }
    @Autowired
    public void setCartService(CartService cartService) { this.cartService = cartService; }

    @Override
    public ResponseEntity<CartDto> addItemToCart(Integer cartId, @Valid CartItemDto cartItemDto) {
        Cart cart = cartService.getCartById(cartId);
        if (cart == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        CartItem item = cartMapper.toCartItem(cartItemDto);
        CartDto cartDto = cartMapper.toCartDto(cartService.addItemToCart(cart, item));
        return ResponseEntity.ok(cartDto);
    }

    @Override
    public ResponseEntity<CartDto> createCart(CartDto cartDto) {
        Cart cart = cartMapper.toCart(cartDto);
        if (cart == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        Cart resCart = cartService.createCart(cart);
        if (resCart == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(cartDto);
    }

    @Override
    public ResponseEntity<CartDto> getCartById(Integer cartId) {
        Cart cart = cartService.getCartById(cartId);
        if (cart == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        return ResponseEntity.ok(cartMapper.toCartDto(cart));
    }

    @Override
    public ResponseEntity<Double> getCartTotal(Integer cartId) {
        Cart cart = cartService.getCartById(cartId);
        if (cart == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        double total = cartService.getCartTotal(cartId);
        return ResponseEntity.ok(total);
    }
}
