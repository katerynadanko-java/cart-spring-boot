package com.kate.cartspringboot.service;

import com.kate.cartspringboot.domain.Product;
import com.kate.cartspringboot.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        return product.get();
    }
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public String createProduct(Product product) {
        String id = String.valueOf(productRepository.save(product).getId());
        return id;
    }

    @Override
    public String deleteById(Long id) {
        productRepository.deleteById(id);
        return String.valueOf(id);
    }

    @Override
    public Product updatePrice(Long productId, BigDecimal price) throws IOException {
        Optional<Product> productRepositoryById = productRepository.findById(productId);
        if (price.compareTo(new BigDecimal(0)) < 0) {
            throw new IOException("Product should not cost less then 0");
        }
        productRepositoryById.get().setPrice(price);
        return productRepositoryById.get();
    }
}
