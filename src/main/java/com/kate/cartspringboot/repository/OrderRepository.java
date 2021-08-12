package com.kate.cartspringboot.repository;

import com.kate.cartspringboot.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
