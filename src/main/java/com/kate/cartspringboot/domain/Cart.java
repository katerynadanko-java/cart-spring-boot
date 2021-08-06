package com.kate.cartspringboot.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor

@Entity
@Table(name = "carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cart_id")
    private Long id;
    private Long customerId;
    @ElementCollection
    private List<ProductAddedInCart> productsAddedInCart;
    private BigDecimal sum;
    @ManyToOne
    private Customer customer;

    public BigDecimal countSum() {
        sum = BigDecimal.ZERO;
        for (ProductAddedInCart p : productsAddedInCart) {
            sum = sum.add(p.getProduct().getCost().multiply(BigDecimal.valueOf(p.getAmount())));
        }
        return sum;
    }

    public Cart(Customer customer) {
        this.customer = customer;
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
