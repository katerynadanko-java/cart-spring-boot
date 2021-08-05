package com.kate.cartspringboot.controller;

import com.kate.cartspringboot.domain.Product;
import com.kate.cartspringboot.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<Product> findById(@PathVariable("id") long id) {
        log.debug("Start to find product with id ", id);
        Product product = productService.findById(id);
        log.debug("Found product with id ", product.getId());
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @Transactional
    @PostMapping(value = "/create")
    public String createProduct(@RequestBody Product product) throws IOException {
        log.debug("Start to create product ", product);
        String createProduct = productService.createProduct(product);
        return createProduct;
    }

    @PutMapping("update/{productId}/{cost}")
    public ResponseEntity<Product> update(@PathVariable Long productId, @PathVariable BigDecimal cost) throws IOException {
        log.debug("Start to update product with id ", productId);
        return ResponseEntity.ok(productService.updatePrice(productId, cost));
    }

    @GetMapping("get")
    private ResponseEntity<List<Product>> getAll() {
        log.debug("Start to find products");
        return ResponseEntity.ok(productService.findAllProducts());
    }

    @DeleteMapping("delete/{productId}")
    public String delete(@PathVariable Long productId) {
        log.debug("Start to delete product with id ", productId);
        ResponseEntity.ok(productService.deleteById(productId));
        return String.valueOf(productId);
    }

    @GetMapping(value = "/findByCost/{cost}")
    public ResponseEntity<List<Product>> findById(@PathVariable BigDecimal cost) throws IOException {
        log.debug("Start to find product with cost ", cost);
        return ResponseEntity.ok(productService.findProductsByCost(cost));
    }

    @GetMapping(value = "/findByName/{name}")
    public ResponseEntity<List<Product>> findByName(@PathVariable String name) throws IOException {
        log.debug("Start to find product with name ", name);
        return ResponseEntity.ok(productService.findProductsByName(name));
    }

}
