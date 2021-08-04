package com.kate.cartspringboot.repository;

import com.kate.cartspringboot.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
    List<Customer> findCustomerByPhone(String phone);

    List<Customer> findCustomerByEmail(String email);


}
