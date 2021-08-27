//package com.kate.cartspringboot.controller;
//
//import com.kate.cartspringboot.converter.CustomerConverter;
//import com.kate.cartspringboot.dto.CustomerDTO;
//import com.kate.cartspringboot.service.CustomerService;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//import java.util.Map;
//
//@Slf4j
//@Controller
//@RequestMapping("api/customer")
//public class CustomerController {
//
//    @Autowired
//    CustomerService customerService;
//
//    @GetMapping("/registration")
//    public String createCustomer(Map<String, Object> model) {
//        model.put("customerDTO", new CustomerDTO());
//        return "Registration";
//    }
//
//    @PostMapping("/home")
//    public String createCustomer(@ModelAttribute("customerDto") CustomerDTO customerDTO) {
//        customerService.createOrUpdateCustomer(CustomerConverter.convertToCustomer(customerDTO));
//        return "redirect:/list";
//    }
//
//    @GetMapping("/list")
//    public String listOfCustomer(Model model) {
//        List<CustomerDTO> customerList = customerService.getAllCustomers();
//        model.addAttribute("customerList", customerList);
//        return "customerList";
//    }
//
//    @PostMapping("/delete")
//    public String deleteCustomer(@RequestParam("id") String id) {
//        customerService.deleteCustomer(Long.parseLong(id));
//        return "redirect:/list";
//    }
//
//    @GetMapping("/edit")
//    public String editCustomer(@RequestParam("id") String id, Map<String, Object> model) {
//        CustomerDTO customerDTO = customerService.editCustomer(Long.parseLong(id));
//        model.put("customerDTO", customerDTO);
//        return "Registration";
//    }
//}








//
//    @GetMapping("/viewCustomers")
//    public String viewCustomers(Model model) {
//        model.addAttribute("customers", customerService.getAllCustomers());
//        return "view-customers";
//    }
//
//    @GetMapping("/addCustomer")
//    public String addCustomerView(Model model) {
//        model.addAttribute("customer", new CustomerDTO());
//        return "add-customer";
//    }
//
//    @PostMapping("/addCustomer")
//    public RedirectView addBook(@ModelAttribute("customer") CustomerDTO customer, RedirectAttributes redirectAttributes) {
//        final RedirectView redirectView = new RedirectView("/customer/addCustomer", true);
//        Customer savedCustomer = customerService.createCustomer(customer);
//        redirectAttributes.addFlashAttribute("savedCustomer", savedCustomer);
//        redirectAttributes.addFlashAttribute("addCustomerSuccess", true);
//        return redirectView;
//    }

//    @Transactional
//    @PostMapping("create")
//    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
//        log.debug("Start to add customer", customer);
//        customerService.createCustomer(customer);
//        return ResponseEntity.ok(customer);
//    }

//    @GetMapping("get")
//    public ResponseEntity<List<Customer>> getAll() {
//        log.debug("Start to find customers");
//        return ResponseEntity.ok(customerService.getAllCustomers());
//    }

//    @Transactional
//    @PutMapping("update/{customerId}/{name}/{surname}")
//    public ResponseEntity<Customer> update(@PathVariable Long customerId, @PathVariable String name, @PathVariable String surname) throws IOException {
//        log.debug("Start to update customer with id", customerId);
//        return ResponseEntity.ok(customerService.updateCustomer(customerId, name, surname));
//    }
//
//    @DeleteMapping("delete/{customerId}")
//    public String delete(@PathVariable Long customerId) {
//        log.debug("Start to delete customer with id", customerId);
//        String deletedCustomer = customerService.deleteCustomer(customerId);
//        return deletedCustomer;
//    }


//}
