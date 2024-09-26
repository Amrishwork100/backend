package com.itech.springsecurity.section3.controller;

import com.itech.springsecurity.section3.Respository.CustomerRepository;
import com.itech.springsecurity.section3.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        String hashPassword = passwordEncoder.encode(customer.getPwd());
        customer.setPwd(hashPassword);
        Customer savedCustomer = customerRepository.save(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCustomer);

    }
}
