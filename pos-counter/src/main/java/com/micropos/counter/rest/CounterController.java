package com.micropos.counter.rest;

import com.micropos.counter.service.CounterService;
import com.micropos.model.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CounterController {

    private CounterService counterService;

    @Autowired
    public void setCounterService(CounterService counterService)  { this.counterService = counterService; }

    @PostMapping("/checkout")
    public ResponseEntity<Double> getTotal(@Valid CartDto cartDto) {
        return ResponseEntity.ok(counterService.getTotal(cartDto));
    }
}
