package com.kate.cartspringboot.repository;

import com.kate.cartspringboot.domain.ProductAddedInCart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductAddedInCartRepository extends JpaRepository<ProductAddedInCart, Long> {

}
