package com.kate.cartspringboot.repository;

import com.kate.cartspringboot.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.math.BigDecimal;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllProductsByPrice(BigDecimal price);

    List<Product> findAllProductsByName(String name);
}
