package com.paymentservice.controller;

import com.paymentservice.entity.Payment;
import com.paymentservice.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/all")
    public List<Payment> retrievePaymentHistory() {
        return paymentService.getPaymentHistory();
    }

    @PostMapping("/accept")
    public Payment makePayment(@RequestBody Payment payment) {
        return paymentService.createPayment(payment);
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable("id") String id) {
        return paymentService.getPaymentById(id);
    }
}
