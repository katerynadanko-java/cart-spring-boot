package com.kate.cartspringboot.converter;

import com.kate.cartspringboot.domain.Cart;
import com.kate.cartspringboot.dto.CartDTO;

public class CartConverter {
    public static CartDTO convertToCartDto(Cart cart) {
        CartDTO newCart = new CartDTO();
        newCart.setId(cart.getId());
        newCart.setSum(cart.getSum());
        newCart.setProducts(cart.getProducts());

        return newCart;
    }

    public static Cart convertToCart(CartDTO cartDto) {
        Cart newCart = new Cart();
        newCart.setId(cartDto.getId());
        newCart.setSum(cartDto.getSum());
        newCart.setProducts(cartDto.getProducts());

        return newCart;
    }
}
