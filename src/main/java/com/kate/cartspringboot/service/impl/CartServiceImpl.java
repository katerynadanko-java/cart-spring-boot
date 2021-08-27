package com.kate.cartspringboot.service.impl;

import com.kate.cartspringboot.domain.Cart;
import com.kate.cartspringboot.domain.Customer;
import com.kate.cartspringboot.domain.Product;
import com.kate.cartspringboot.dto.CartDTO;
import com.kate.cartspringboot.repository.CartRepository;
import com.kate.cartspringboot.repository.CustomerRepository;
import com.kate.cartspringboot.repository.ProductRepository;
import com.kate.cartspringboot.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
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
    List<Product> products = new ArrayList<>();
    List<Cart> carts = new ArrayList<>();

    @Override
    public Cart createCart(Long customerId) throws IOException {

        if (!customerRepository.existsById(customerId)) {
            throw new IOException("Person with id " + customerId + " does not exists.");
        }
        Cart cart = new Cart();
        Customer customer = customerRepository.findById(customerId).get();
        Cart savedCart = cartRepository.save(cart);
        carts.add(cart);

        customer.setCarts(carts);
        customerRepository.save(customer);
        return savedCart;
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    @Override
    public Cart addProductsToCart(Long cartId, Long productId, Integer amount) {

        Product product = productRepository.getById(productId);
        Optional<Cart> cartOptional = cartRepository.findById(cartId);
        Cart cart = cartOptional.get();
        product.setAmount(amount);
        products.add(product);
        cart.setProducts(products);
        BigDecimal bigDecimalSum = countSum(cart);
        cart.setSum(bigDecimalSum);
        return cartRepository.save(cart);
    }

    @Override
    public Cart updateProductsFromCart(Long cartId, Long productId, Integer amount) throws IOException {
        if (!cartRepository.existsById(cartId)) {
            throw new IOException("Cart with id " + cartId + " does not exists");
        }
        if (productId == null) {
            throw new IOException("Required parameters: productId");
        }
        Cart cart = cartRepository.getById(cartId);
        for (Product p : cart.getProducts()) {
            if (productId.equals(p.getId())) {
                p.setAmount(amount);
                BigDecimal bigDecimalSum = countSum(cart);
                cart.setSum(bigDecimalSum);
                cartRepository.save(cart);
                break;
            }
        }
        return cartRepository.save(cart);
    }

    @Override
    public Cart deleteProductFromCart(Long cartId, Long productId) throws IOException {
        if (!cartRepository.existsById(cartId)) {
            throw new IOException("Cart with id " + cartId + " does not exists");
        }
        if (productId == null) {
            throw new IOException("Required parameters: productId");
        }
        Cart cart = cartRepository.getById(cartId);
        for (Product p : cart.getProducts()) {
            if (productId.equals(p.getId())) {
                p.setAmount((p.getAmount()) - 1);
                BigDecimal bigDecimalSum = countSum(cart);
                cart.setSum(bigDecimalSum);
                cartRepository.save(cart);
                break;
            }
        }
        return cartRepository.save(cart);
    }

    @Override
    public Cart deleteAllProductsFromCart(Long cartId) {
        Cart cart = cartRepository.getById(cartId);
        cart.getProducts().clear();
        BigDecimal bigDecimalSum = BigDecimal.ZERO;
        cart.setSum(bigDecimalSum);
        return cartRepository.save(cart);
    }

    public BigDecimal countSum(Cart cart) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Product p : cart.getProducts()) {
            sum = sum.add(p.getPrice().multiply(BigDecimal.valueOf(p.getAmount())));
        }
        cart.setSum(sum);
        return sum;
    }

    private static CartDTO convertToCartDto(Cart cart) {
        CartDTO newCart = new CartDTO();
        newCart.setId(cart.getId());
        newCart.setProducts(cart.getProducts());
        newCart.setSum(cart.getSum());
        return newCart;
    }

    private static Cart convertToCart(CartDTO cartDto) {
        Cart newCart = new Cart();
        newCart.setSum(cartDto.getSum());
        newCart.setId(cartDto.getId());
        newCart.setProducts(cartDto.getProducts());
        return newCart;
    }

}

