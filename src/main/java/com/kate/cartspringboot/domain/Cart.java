package com.kate.cartspringboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long customerId;
    @ElementCollection
    private List<ProductAddedInCart> productsAddedInCart;
    private BigDecimal sum;

    public BigDecimal countSum() {
        sum = BigDecimal.ZERO;
        for (ProductAddedInCart p : productsAddedInCart) {
            sum = sum.add(p.getProduct().getCost().multiply(BigDecimal.valueOf(p.getAmount())));
        }
        return sum;
    }

    public void addProductToCart(ProductAddedInCart productAddedInCart) {
        if (productAddedInCart == null) {
            productsAddedInCart = new ArrayList<ProductAddedInCart>();
        }
        productsAddedInCart.add(productAddedInCart);
        countSum();
    }

    public void removeProductFromCart(ProductAddedInCart productAddedInCart) {
        if (productsAddedInCart != null) {
            productsAddedInCart.remove(productAddedInCart);
            countSum();
        }
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", customerId=" + customerId +
                ", productsAddedInCart=" + productsAddedInCart +
                ", sum=" + sum +
                '}';
    }
}
