package com.kate.cartspringboot.converter;

import com.kate.cartspringboot.domain.Product;
import com.kate.cartspringboot.dto.ProductDTO;

public class ProductConverter {
    public static ProductDTO convertToProductDto(Product product) {
        ProductDTO newProduct = new ProductDTO();
        newProduct.setId(product.getId());
        newProduct.setName(product.getName());
        newProduct.setPrice(product.getPrice());
        newProduct.setAmount(product.getAmount());

        return newProduct;
    }

    public static Product convertToProduct(ProductDTO productDto) {
        Product newProduct = new Product();
        newProduct.setId(productDto.getId());
        newProduct.setName(productDto.getName());
        newProduct.setPrice(productDto.getPrice());
        newProduct.setAmount(productDto.getAmount());

        return newProduct;
    }
}
