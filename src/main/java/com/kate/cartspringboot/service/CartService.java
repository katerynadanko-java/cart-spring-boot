package com.kate.cartspringboot.service;

import com.kate.cartspringboot.domain.Cart;

import java.io.IOException;
import java.util.List;

public interface CartService {

    Cart createCart(Long customerId) throws IOException;

    List<Cart> getAllCarts();

    Cart addProductsToCart(Long cartId, Long productId, Integer amount);

    Cart updateProductsFromCart(Long cartId, Long productId, Integer amount) throws IOException;

    Cart deleteProductFromCart(Long cartId, Long productId) throws IOException;

    Cart deleteAllProductsFromCart(Long cartId);

}
