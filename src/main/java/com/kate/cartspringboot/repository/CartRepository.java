package com.kate.cartspringboot.repository;

import com.kate.cartspringboot.domain.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Long> {

    Optional<Cart> findById(Long id);

}
