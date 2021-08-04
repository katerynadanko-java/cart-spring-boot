package com.kate.cartspringboot.service;

import com.kate.cartspringboot.domain.Cart;
import com.kate.cartspringboot.domain.Customer;
import com.kate.cartspringboot.domain.ProductAddedInCart;
import com.kate.cartspringboot.repository.CartRepository;
import com.kate.cartspringboot.repository.CustomerRepository;
import com.kate.cartspringboot.repository.ProductAddedInCartRepository;
import com.kate.cartspringboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductAddedInCartRepository productAddedInCartRepository;

    private List<Cart> carts;

    @Override
    public Cart createCart(Long customerId) throws IOException {
        if (!cartRepository.existsById(customerId)) {
            throw new IOException("There is no customer with id " + customerId);
        }
        Cart cart = new Cart();
        cart.setCustomerId(customerId);
        Customer customer = customerRepository.getById(customerId);
        customer.addCart(cart);
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public List<Cart> getAllCartsByCustomerId(Long customerId) {
        if (customerId == null) {
            return cartRepository.findAll();
        }
        return cartRepository.findAllCartsByCustomerId(customerId);
    }

    @Override
    public Cart addProductToCart(Long cartId, Long productId, Integer amount) throws IOException {

        if (!cartRepository.existsById(cartId)) {
            throw new IOException("Cart with id " + cartId + " does not exists");
        }
        if (productId == null || amount == null) {
            throw new IOException("Required parameters: productId, quantity");
        }
        if (!productRepository.existsById(productId)) {
            throw new IOException("Product with id " + productId + " does not exists");
        }
        if (amount < 1) {
            throw new IOException("Amount of products should be more than 1!");
        }

        ProductAddedInCart productAddedInCart = new ProductAddedInCart();
        productAddedInCart.setProduct(productRepository.getById(productId));
        productAddedInCart.setAmount(amount);
        productAddedInCartRepository.save(productAddedInCart);

        Optional<Cart> cartRepositoryById = cartRepository.findById(cartId);
        cartRepositoryById.get().addProductToCart(productAddedInCart);
        cartRepository.saveAndFlush(cartRepositoryById.get());
        return cartRepositoryById.get();
    }

    @Override
    public Cart deleteProductFromCart(Long cartId, Long productId) throws IOException {
        if (!cartRepository.existsById(cartId)) {
            throw new IOException("Cart with id " + cartId + " does not exists");
        }
        if (!productRepository.existsById(productId)) {
            throw new IOException("Product with id " + productId + " does not exists");
        }
        if (productId == null) {
            throw new IOException("Required parameters: productId");
        }
        Cart cart = cartRepository.getById(cartId);
        for (ProductAddedInCart p : cart.getProductsAddedInCart()) {
            if (p.getProduct().getId().equals(productId)) {
                productAddedInCartRepository.delete(p);
                break;
            }
        }
        return cart;
    }

    @Override
    public String deleteCartById(Long cartId) throws IOException {
        if (!cartRepository.existsById(cartId)) {
            throw new IOException("Cart with id " + cartId + " does not exists");
        }
        cartRepository.deleteById(cartId);
        return "Cart with id " + cartId + " deleted successfully";
    }

    @Override
    public List<Cart> getAllPremiumCarts(BigDecimal sum) {
        for (Cart c : carts) {
            if (c.countSum().compareTo(sum) < 0) {
                return null;
            }
        }
        return cartRepository.findAllCartsBySum(sum);
    }

    @Override
    public Cart updateCart(List<ProductAddedInCart> products, Long cartId) throws IOException {
        Optional<Cart> cartRepositoryById = cartRepository.findById(cartId);
        if (products == null && products.isEmpty()) {
            throw new IOException("There are no selected products");
        }
        if (!cartRepository.getById(cartId).getProductsAddedInCart().equals(products)) {
            cartRepositoryById.get().setProductsAddedInCart(products);
        }
        return cartRepositoryById.get();
    }

}
