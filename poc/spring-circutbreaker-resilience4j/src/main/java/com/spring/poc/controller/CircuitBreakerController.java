package com.spring.poc.controller;

import com.spring.poc.model.Payment;
import com.spring.poc.service.CircuitBreakerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CircuitBreakerController {

    @Autowired
    private CircuitBreakerService circuitBreakerService;

    @GetMapping("/circuit-breaker/paymentAll")
    public List<Payment> retrieve(){
        return circuitBreakerService.paymentHistory();

    }
}
