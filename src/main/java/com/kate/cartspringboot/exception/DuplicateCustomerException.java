package com.kate.cartspringboot.exception;

import com.kate.cartspringboot.domain.Customer;
import com.kate.cartspringboot.dto.CustomerDTO;
import lombok.Data;

@Data
public class DuplicateCustomerException extends RuntimeException {
    private final Customer customer;

    public DuplicateCustomerException(Customer customer) {
        this.customer = customer;
    }
}