package com.kate.cartspringboot.service;

import com.kate.cartspringboot.domain.Customer;
import com.kate.cartspringboot.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    List<Customer> customers;

    @Override
    public void createCustomer(Customer customer) throws IOException {
        if (customer.getPhone().isEmpty())
            throw new IOException("Required parameters: customerPhone");
        if (customer.getEmail().isEmpty())
            throw new IOException("Required parameters: customerEmail");
        for (Customer c : customers) {
            if (customerRepository.findCustomerByPhone(customer.getPhone()).equals(c.getPhone())) {
                throw new IOException("Customer with phone " + c.getPhone() + " already exist");
            }
        }
        for (Customer c : customers) {
            if (customerRepository.findCustomerByEmail(customer.getEmail()).equals(c.getEmail())) {
                throw new IOException("Customer with email " + c.getEmail() + " already exist");
            }
        }
        if (!customer.getEmail().matches("[@]{1}\\.+")) {
            throw new IOException("Wrong value for email");
        }
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer updateCustomer(Long customerId, String name, String surname) throws IOException {
        if (!customerRepository.existsById(customerId)) {
            throw new IOException("Customer with id " + customerId + " does not exist");
        }
        if (name == null && name.isEmpty()) {
            throw new IOException("Required parameters: customerName");
        }
        if (surname == null && surname.isEmpty()) {
            throw new IOException("Required parameters: customerSurname");
        }
        Optional<Customer> customerRepositoryById = customerRepository.findById(customerId);
        if (name != null) {
            customerRepositoryById.get().setName(name);
        }
        if (surname != null) {
            customerRepositoryById.get().setSurname(surname);
        }
        return customerRepositoryById.get();
    }

    @Override
    public String deleteCustomer(Long customerId) {
        customerRepository.deleteById(customerId);
        return "Customer with id " + customerId + " deleted successfully";
    }

    @Override
    public List<Customer> findByPhone(String phone) throws IOException {
        if (phone == null && phone.isEmpty()) {
            throw new IOException("Required parameters: customerPhone");
        }
        for (Customer c : customers) {
            if (!phone.equals(c.getPhone())) {
                throw new IOException("Customer with phone " + phone + " does not exist");
            }
        }
        return customerRepository.findCustomerByPhone(phone);
    }

    @Override
    public List<Customer> findByEmail(String email) throws IOException {
        if (email == null && email.isEmpty()) {
            throw new IOException("Required parameters: customerEmail");
        }
        for (Customer c : customers) {
            if (!email.equals(c.getEmail())) {
                throw new IOException("Customer with phone " + email + " does not exist");
            }
        }
        return customerRepository.findCustomerByEmail(email);
    }
}
