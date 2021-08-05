package com.kate.cartspringboot.controller;

import com.kate.cartspringboot.domain.Customer;
import com.kate.cartspringboot.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @Transactional
    @PostMapping("create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        log.debug("Start to add customer", customer);
        customerService.createCustomer(customer);
        return ResponseEntity.ok(customer);
    }

    @GetMapping("get")
    public ResponseEntity<List<Customer>> getAll() {
        log.debug("Start to find customers");
        return ResponseEntity.ok(customerService.getAllCustomers());
    }

    @PutMapping("update/{customerId}/{name}/{surname}")
    public ResponseEntity<Customer> update(@PathVariable Long customerId, @PathVariable String name, @PathVariable String surname) throws IOException {
        log.debug("Start to update customer with id", customerId);
        return ResponseEntity.ok(customerService.updateCustomer(customerId, name, surname));
    }

    @DeleteMapping("delete/{customerId}")
    public String delete(@PathVariable Long customerId) {
        log.debug("Start to delete customer with id", customerId);
        String deletedCustomer = customerService.deleteCustomer(customerId);
        return deletedCustomer;
    }

    @GetMapping("getByPhone/{phone}")
    public ResponseEntity<List<Customer>> findByPhone(@PathVariable String phone) throws IOException {
        log.debug("Start to find customer with phone", phone);
        return ResponseEntity.ok(customerService.findByPhone(phone));
    }

    @GetMapping("getByEmail/{email}")
    public ResponseEntity<List<Customer>> findByEmail(@PathVariable String email) throws IOException {
        log.debug("Start to find customer with email", email);
        return ResponseEntity.ok(customerService.findByEmail(email));
    }
}
