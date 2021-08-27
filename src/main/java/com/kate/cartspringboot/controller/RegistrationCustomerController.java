package com.kate.cartspringboot.controller;

import com.kate.cartspringboot.dto.CustomerDTO;
import com.kate.cartspringboot.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

import static com.kate.cartspringboot.converter.CustomerConverter.convertToCustomer;

@Controller
public class RegistrationCustomerController {

    @Autowired
    private CustomerService customerService;

    @GetMapping("/registration")
    public String createCustomer(Map<String, Object> model) {
        model.put("customerDTO", new CustomerDTO());
        return "СustomerRegistration";
    }

    @PostMapping("/home")
    public String createCustomer(@ModelAttribute("customerDto") CustomerDTO customerDTO) {
        customerService.createOrUpdateCustomer(convertToCustomer(customerDTO));
        return "redirect:/list";
    }

    @GetMapping("/list")
    public String listOfCustomer(Model model) {
        List<CustomerDTO> customerList = customerService.getAllCustomers();
        model.addAttribute("customerList", customerList);
        return "customerList";
    }

    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam("id") String id) {
        customerService.deleteCustomer(Long.parseLong(id));
        return "redirect:/list";
    }

    @GetMapping("/edit")
    public String editCustomer(@RequestParam("id") String id, Map<String, Object> model) {
        CustomerDTO customerDTO = customerService.editCustomer(Long.parseLong(id));
        model.put("customerDTO", customerDTO);
        return "СustomerRegistration";
    }

}
