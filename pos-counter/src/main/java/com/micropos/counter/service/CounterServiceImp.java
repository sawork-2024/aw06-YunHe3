package com.micropos.counter.service;

import com.micropos.model.CartDto;
import com.micropos.model.CartItemDto;
import com.micropos.model.ProductDto;
import org.springframework.stereotype.Component;

@Component
public class CounterServiceImp implements CounterService{
    @Override
    public Double getTotal(CartDto cartDto) {
        double total = 0.0;
        for (CartItemDto cartItemDto : cartDto.getItems()) {
            ProductDto productDto = cartItemDto.getProduct();
            total += productDto.getPrice() * cartItemDto.getAmount();
        }
        return total;
    }
}
