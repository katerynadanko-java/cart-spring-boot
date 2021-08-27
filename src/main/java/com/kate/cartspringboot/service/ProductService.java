package com.kate.cartspringboot.service;

import com.kate.cartspringboot.domain.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product findById(Long id);

    List<Product> getAll();

    Product createProduct(Product product);

    String deleteById(Long id);

    Product updatePrice(Long productId, BigDecimal cost);



}
