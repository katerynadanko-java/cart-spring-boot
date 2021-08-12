package com.kate.cartspringboot.repository;

import com.kate.cartspringboot.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {

//    List<Cart> findAllCartsByCustomerId(Long customerId);

//    List<Cart> findAllCartsBySum(BigDecimal sum);
}
