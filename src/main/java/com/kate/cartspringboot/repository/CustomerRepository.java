package com.kate.cartspringboot.repository;

import com.kate.cartspringboot.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
