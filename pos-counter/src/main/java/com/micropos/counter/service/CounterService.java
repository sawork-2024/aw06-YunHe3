package com.micropos.counter.service;

import com.micropos.model.CartDto;

public interface CounterService {

    Double getTotal(CartDto cartDto);
}
