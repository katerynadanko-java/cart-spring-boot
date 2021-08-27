package com.kate.cartspringboot.service.impl;

import com.kate.cartspringboot.domain.Customer;
import com.kate.cartspringboot.dto.CustomerDTO;
import com.kate.cartspringboot.repository.CustomerRepository;
import com.kate.cartspringboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.kate.cartspringboot.converter.CustomerConverter.convertToCustomerDto;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
@Override
    public void createOrUpdateCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public List<CustomerDTO> getAllCustomers() {
        List<Customer> list = customerRepository.findAll();
        return list.stream()
                .map(CustomerDTO::new)
                .collect(Collectors.toCollection(ArrayList::new));
    }
    @Override
    public void deleteCustomer(Long id) {
        customerRepository.deleteById(id);
    }
    @Override
    public CustomerDTO editCustomer(Long id) {
        Customer emp = customerRepository.getOne(id);
        return convertToCustomerDto(emp);
    }
}
