package com.kate.cartspringboot.service;

import com.kate.cartspringboot.domain.Product;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product findById(Long id);

    String createProduct(Product product) throws IOException;

    String deleteById(Long id);

    Product updatePrice(Long productId, BigDecimal cost) throws IOException;

    List<Product> findAllProducts();

    List<Product> findProductsByCost(BigDecimal cost) throws IOException;

    List<Product> findProductsByName(String name) throws IOException;


}
