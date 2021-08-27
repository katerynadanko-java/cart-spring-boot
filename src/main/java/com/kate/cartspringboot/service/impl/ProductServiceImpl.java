package com.kate.cartspringboot.service.impl;

import com.kate.cartspringboot.domain.Product;
import com.kate.cartspringboot.dto.ProductDTO;
import com.kate.cartspringboot.repository.ProductRepository;
import com.kate.cartspringboot.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Product createProduct(Product product) {
        String id = String.valueOf(productRepository.save(product).getId());
        return product;
    }

    @Override
    public String deleteById(Long id) {
        productRepository.deleteById(id);
        return String.valueOf(id);
    }

    @Override
    public Product updatePrice(Long productId, BigDecimal price) {
        Optional<Product> productRepositoryById = productRepository.findById(productId);
        if (price.compareTo(new BigDecimal(0)) < 0) {
            throw new RuntimeException("Product should not cost less then 0");
        }
        productRepositoryById.get().setPrice(price);
        return productRepositoryById.get();
    }

    private static ProductDTO convertToProductDto(Product product) {
        ProductDTO newProduct = new ProductDTO();
        newProduct.setId(product.getId());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        return newProduct;
    }

    private static Product convertToProduct(ProductDTO productDto) {
        Product newProduct = new Product();
        newProduct.setId(productDto.getId());
        newProduct.setName(productDto.getName());
        newProduct.setPrice(productDto.getPrice());
        return newProduct;
    }
}
