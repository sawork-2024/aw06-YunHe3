package com.micropos.carts.service;

import com.micropos.carts.mapper.CartMapper;
import com.micropos.carts.model.Cart;
import com.micropos.carts.model.CartItem;
import com.micropos.carts.repository.CartRepository;
import com.micropos.model.CartDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
public class CartServiceImp implements CartService{

    private CartRepository cartRepository;
    private CartMapper cartMapper;
    @Autowired
    public void setCartRepository(CartRepository cartRepository) { this.cartRepository = cartRepository; }
    @Autowired
    public void setCartMapper(CartMapper cartMapper) { this.cartMapper = cartMapper; }
    @LoadBalanced
    private RestTemplate restTemplate;
    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) { this.restTemplate = restTemplate; }

    private static final String COUNTER_URL = "http://COUNTER_SERVICE/counter";

    @Override
    public Cart addItemToCart(Cart cart, CartItem cartItem) {
        cart.getCartItemList().add(cartItem);
        return cartRepository.save(cart);
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Cart getCartById(Integer cartId) {
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        return cartOptional.orElse(null);
    }

    @Override
    public Double getCartTotal(Integer cartId) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CartDto> request = new HttpEntity<>(cartMapper.toCartDto(cartRepository.findById(cartId).get()), headers);
        return restTemplate.postForObject(COUNTER_URL + "/checkout", request, Double.class);
    }
}
