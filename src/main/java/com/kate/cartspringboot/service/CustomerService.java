package com.kate.cartspringboot.service;

import com.kate.cartspringboot.domain.Customer;

import java.io.IOException;
import java.util.List;

public interface CustomerService {
    void createCustomer(Customer customer);

    List<Customer> getAllCustomers();

    Customer updateCustomer(Long id, String name, String surname) throws IOException;

    String deleteCustomer(Long id);

    List<Customer> findByPhone(String phone) throws IOException;

    List<Customer> findByEmail(String email) throws IOException;

}
