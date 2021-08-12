package com.kate.cartspringboot.repository;

import com.kate.cartspringboot.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findById(Long id);

    List<Customer> findCustomerByPhone(String phone);

    List<Customer> findCustomerByEmail(String email);


}
