package com.kate.cartspringboot.dto;

import com.kate.cartspringboot.domain.Cart;
import com.kate.cartspringboot.domain.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartDTO {
    private Long id;
    private BigDecimal sum = BigDecimal.ZERO;
    private List<Product> products;

}
