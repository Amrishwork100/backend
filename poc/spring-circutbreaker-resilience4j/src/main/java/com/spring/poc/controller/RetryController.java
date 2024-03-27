package com.spring.poc.controller;

import com.spring.poc.model.Payment;
import com.spring.poc.service.RetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class RetryController {
    @Autowired
    private RetryService retryService;

    @GetMapping("/retry/paymentAll")
    public List<Payment> retrievePaymentHistory() {
        return retryService.retryPaymentHistory();

    }
}
