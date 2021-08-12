package com.kate.cartspringboot.service;

import com.kate.cartspringboot.domain.Cart;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    Cart createCart(Long customerId) throws IOException;


    List<Cart> getAllCarts();

    Cart addOrderToCart(Long cartId, Long productId, Integer amount) throws IOException;

    Cart deleteOrderFromCart(Long cartId, Long productId) throws IOException;

    String deleteCartById(Long cartId) throws IOException;

    BigDecimal countSum(Cart cart);

//    List<Cart> getAllCartsBySum(BigDecimal sum);
}
