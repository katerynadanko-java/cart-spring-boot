package com.kate.cartspringboot.service;

import com.kate.cartspringboot.domain.Customer;
import com.kate.cartspringboot.dto.CustomerDTO;

import java.io.IOException;
import java.util.List;

public interface CustomerService {
    void createOrUpdateCustomer(Customer customer);

    List<CustomerDTO> getAllCustomers();

    void deleteCustomer(Long id);

    CustomerDTO editCustomer(Long id);

}
