package com.kate.cartspringboot.converter;

import com.kate.cartspringboot.domain.Customer;
import com.kate.cartspringboot.dto.CustomerDTO;

public class CustomerConverter {
    public static CustomerDTO convertToCustomerDto(Customer customer) {
        CustomerDTO newCustomer = new CustomerDTO();
        newCustomer.setId(customer.getId());
        newCustomer.setFirstName(customer.getFirstName());
        newCustomer.setUserName(customer.getUserName());
        newCustomer.setLastName(customer.getLastName());
        newCustomer.setPhone(customer.getPhone());
        newCustomer.setPassword(customer.getPassword());
        newCustomer.setCarts(customer.getCarts());

        return newCustomer;
    }

    public static Customer convertToCustomer(CustomerDTO customerDto) {
        Customer newCustomer = new Customer();
        newCustomer.setId(customerDto.getId());
        newCustomer.setFirstName(customerDto.getFirstName());
        newCustomer.setUserName(customerDto.getUserName());
        newCustomer.setLastName(customerDto.getLastName());
        newCustomer.setPhone(customerDto.getPhone());
        newCustomer.setPassword(customerDto.getPassword());
        newCustomer.setCarts(customerDto.getCarts());

        return newCustomer;
    }
}
