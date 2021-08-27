package com.kate.cartspringboot.dto;

import com.kate.cartspringboot.domain.Cart;
import com.kate.cartspringboot.domain.Customer;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import java.util.List;

@Data
public class CustomerDTO {
    private Long id;
    private String userName;
    private String firstName;
    private String lastName;
    private String phone;
    private String password;
    private List<Cart> carts;

    public CustomerDTO() {
    }

    public CustomerDTO(Customer customer) {
        this.id = customer.getId();
        this.userName = customer.getUserName();
        this.firstName = customer.getFirstName();
        this.lastName = customer.getLastName();
        this.phone = customer.getPhone();
        this.password = customer.getPassword();
    }

    public CustomerDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
