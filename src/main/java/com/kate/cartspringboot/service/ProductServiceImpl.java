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
    public Product updatePrice(Long productId, BigDecimal cost) throws IOException {
        Optional<Product> productRepositoryById = productRepository.findById(productId);
        if (cost.compareTo(new BigDecimal(0)) < 0) {
            throw new IOException("Product should not cost less then 0");
        }
        productRepositoryById.get().setCost(cost);
        return productRepositoryById.get();
    }

    @Override
    public List<Product> findProductsByCost(BigDecimal cost) throws IOException {
        if (cost.compareTo(new BigDecimal(0)) < 0) {
            throw new IOException("Product should cost more then 0");
        }
        return productRepository.findAllProductsByCost(cost);
    }

    @Override
    public List<Product> findProductsByName(String name) throws IOException {

        if (name == null && name.isEmpty()) {
            throw new IOException("Required parameters: productName");
        }
        return productRepository.findAllProductsByName(name);
    }
}
