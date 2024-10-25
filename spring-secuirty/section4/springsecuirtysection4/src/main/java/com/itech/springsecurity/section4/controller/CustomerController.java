package com.itech.springsecurity.section4.controller;

import com.itech.springsecurity.section4.repository.CustomerRepository;
import com.itech.springsecurity.section4.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController

public class CustomerController {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    private List<Customer> list = new ArrayList<>();

    @PostMapping("/register")
    public List<Customer> createCustomer(@RequestBody Customer customer) {
        String hashPassword = passwordEncoder.encode(customer.getPwd());
        customer.setPwd(hashPassword);
        list.add(customer);
        return list;
        //Customer savedCustomer = customerRepository.save(customer);
        //return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);

    }
}
