package com.kate.cartspringboot.service;

import com.kate.cartspringboot.domain.Cart;
import com.kate.cartspringboot.domain.Customer;
import com.kate.cartspringboot.domain.Order;
import com.kate.cartspringboot.domain.Product;
import com.kate.cartspringboot.repository.CartRepository;
import com.kate.cartspringboot.repository.CustomerRepository;
import com.kate.cartspringboot.repository.OrderRepository;
import com.kate.cartspringboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
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
    private OrderRepository orderRepository;

    private Cart cart;

    @Override
    public Cart createCart(Long customerId) throws IOException {

        if (!customerRepository.existsById(customerId)) {
            throw new IOException("Person with id " + customerId + " does not exists.");
        }
        Cart cart = new Cart();
        Cart savedCart = cartRepository.save(cart);
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        Customer customer = customerOptional.get();
        customer.setCart(savedCart);
        customerRepository.save(customer);
        return cart;
    }

    @Override
    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }


    @Override
    public Cart addOrderToCart(Long cartId, Long productId, Integer amount) throws IOException {

//        if (!cartRepository.existsById(cartId)) {
//            throw new IOException("Cart with id " + cartId + " does not exists");
//        }
//        if (productId == null || amount == null) {
//            throw new IOException("Required parameters: productId, quantity");
//        }
//        if (!productRepository.existsById(productId)) {
//            throw new IOException("Product with id " + productId + " does not exists");
//        }
//        if (amount < 1) {
//            throw new IOException("Amount of products should be more than 1!");
//        }

        Cart cart = cartRepository.findById(cartId).get();
        productRepository.getById(productId);
        for (Order o : cart.getOrders()) {
            productId.equals(o.getProduct().getId());
            o.setAmount(o.getAmount() + amount);
            updateCart(cart);
        }
        Product product = productRepository.getById(productId);
        Order order = new Order();
        order.setProduct(product);
        order.setAmount(amount);
        cart.getOrders().add(order);
        return updateCart(cart);
    }


//        Order order = new Order();
//        order.setProduct(productRepository.getById(productId));
//        order.setAmount(amount);
//        orderRepository.save(order);
//
//        Optional<Cart> cartOptional = cartRepository.findById(cartId);
//        cartOptional.get().addOrderToCart(order);
//        cartRepository.saveAndFlush(cartOptional.get());
//        return cartOptional.get();



private Cart updateCart(Cart cart){
    BigDecimal bigDecimalSum = countSum(cart);
    cart.setSum(bigDecimalSum);
    return cartRepository.save(cart);
}
    @Override
    public Cart deleteOrderFromCart(Long cartId, Long productId) throws IOException {
        if (!cartRepository.existsById(cartId)) {
            throw new IOException("Cart with id " + cartId + " does not exists");
        }
        if (productId == null) {
            throw new IOException("Required parameters: productId");
        }
        Cart cart = cartRepository.getById(cartId);
        for (Order p : cart.getOrders()) {
            if (p.getProduct().getId().equals(productId)) {
                orderRepository.delete(p);
                break;
            }
        }
        return cart;
    }

    @Override
    public String deleteCartById(Long cartId) {
        cartRepository.deleteById(cartId);
        return "Cart with id " + cartId + " deleted successfully";
    }

    @Override
    public BigDecimal countSum(Cart cart) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Order o : cart.getOrders()) {
            sum = sum.add(o.getProduct().getPrice().multiply(BigDecimal.valueOf(o.getAmount())));
        }
        cart.setSum(sum);
        return sum;
    }




//    @Override
//    public List<Cart> getAllCartsBySum(BigDecimal sum) {
//        for (Cart c : carts) {
//            if (c.countSum().compareTo(sum) < 0) {
//                return null;
//            }
//        }
//        return cartRepository.findAllCartsBySum(sum);
//    }

}

