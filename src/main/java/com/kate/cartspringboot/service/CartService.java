package com.kate.cartspringboot.service;

import com.kate.cartspringboot.domain.Cart;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface CartService {
    Cart createCart(Long customerId) throws IOException;

    List<Cart> getAllCartsByCustomerId(Long customerId) throws IOException;

    List<Cart> getAllCarts();

    Cart addProductToCart(Long cartId, Long productId, Integer amount) throws IOException;

    Cart deleteProductFromCart(Long cartId, Long productId) throws IOException;

    String deleteCartById(Long cartId) throws IOException;

    List<Cart> getAllCartsBySum(BigDecimal sum);
}
