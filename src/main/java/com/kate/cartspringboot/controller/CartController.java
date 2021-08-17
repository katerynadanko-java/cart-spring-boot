package com.kate.cartspringboot.controller;

import com.kate.cartspringboot.domain.Cart;
import com.kate.cartspringboot.service.CartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/cart")
public class CartController {

    @Autowired
    CartService cartService;

    @Transactional
    @PostMapping("create/{customerId}")
    public ResponseEntity<Cart> createCart(@PathVariable Long customerId) throws IOException {
        log.debug("Star to add cart with customerId ", customerId);
        return ResponseEntity.ok(cartService.createCart(customerId));
    }

    @GetMapping("get")
    public ResponseEntity<List<Cart>> getAllCarts() {
        log.debug("Start to find carts");
        return ResponseEntity.ok(cartService.getAllCarts());
    }

    @PutMapping("/{cartId}/add/product/{productId}/{amount}")
    public ResponseEntity<Cart> addProductToCart(@PathVariable Long cartId, @PathVariable Long productId, @PathVariable Integer amount) throws IOException {
        log.debug("Start to add product with id ", productId, "to customer with id", cartId);
        return ResponseEntity.ok(cartService.addOrderToCart(cartId, productId, amount));
    }

    @DeleteMapping("/{cartId}/delete/product/{productId}")
    public ResponseEntity<Cart> deleteProductToCart(@PathVariable Long cartId, @PathVariable Long productId) throws IOException {
        log.debug("Start to delete product with id ", productId);
        return ResponseEntity.ok(cartService.deleteOrderFromCart(cartId, productId));
    }

    @DeleteMapping("/delete/{cartId}")
    public String deleteCart(@PathVariable Long cartId) throws IOException {
        log.debug("Start to delete cart with id ", cartId);
        String deletedCart = cartService.deleteCartById(cartId);
        return deletedCart;
    }

}
