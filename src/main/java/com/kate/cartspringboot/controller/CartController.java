package com.kate.cartspringboot.controller;

import com.kate.cartspringboot.domain.Cart;
import com.kate.cartspringboot.domain.ProductAddedInCart;
import com.kate.cartspringboot.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping("create")
    public ResponseEntity<Cart> createCart(Long customerId) throws IOException {
        log.debug("Star to add cart with customerId ", customerId);
        return ResponseEntity.ok(cartService.createCart(customerId));
    }

    @GetMapping("get")
    public ResponseEntity<List<Cart>> getAllCarts(Long customerId) throws IOException {
        log.debug("Star to find cart with customerId ", customerId);
        return ResponseEntity.ok(cartService.getAllCartsByCustomerId(customerId));
    }

    @PutMapping("/{cartId}/add/product")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long cartId, Long productId, Integer amount) throws IOException {
        log.debug("Star to add product with id ", productId, "to cart with id", cartId);
        return ResponseEntity.ok(cartService.addProductToCart(cartId, productId, amount));
    }

    @DeleteMapping("/{cartId}/delete/product")
    public ResponseEntity<Cart> deleteProductToCart(@PathVariable Long cartId, Long productId) throws IOException {
        log.debug("Star to delete product with id ", productId);
        return ResponseEntity.ok(cartService.deleteProductFromCart(cartId, productId));
    }

    @DeleteMapping("/delete/{cartId}")
    public String deleteCart(@PathVariable Long cartId) throws IOException {
        log.debug("Star to delete cart with id ", cartId);
        String deletedCart = cartService.deleteCartById(cartId);
        return deletedCart;
    }

    @GetMapping("/{cartId}/getPremium")
    public ResponseEntity<List<Cart>> getPremiumCart(@PathVariable BigDecimal sum) throws IOException {
        log.debug("Star to find premiumCarts with sum ", sum);
        return ResponseEntity.ok(cartService.getAllPremiumCarts(sum));
    }

    @PutMapping("/update/{cartId}")
    public ResponseEntity<Cart> updateCart(@PathVariable List<ProductAddedInCart> products, Long cartId) throws IOException {
        log.debug("Star to update cart with id ", cartId);
        return ResponseEntity.ok(cartService.updateCart(products, cartId));
    }
}
