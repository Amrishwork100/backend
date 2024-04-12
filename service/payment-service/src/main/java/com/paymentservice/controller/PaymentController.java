package com.paymentservice.controller;

import com.paymentservice.entity.Payment;
import com.paymentservice.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/all")
    public List<Payment> retrievePaymentHistory() {
        List<Payment> payments = paymentService.getPaymentHistory();
        payments.forEach(payment -> {
            log.info(" Return all payment details {} : ", payment);
        });
        return payments;
    }

    @PostMapping("/accept")
    public Payment makePayment(@RequestBody Payment payment) {
        Payment pay = paymentService.createPayment(payment);
        log.info("Make the Payment {} : ", pay);
        return pay;
    }

    @GetMapping("/{id}")
    public Payment getPaymentById(@PathVariable("id") String id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("Get the Payment by Id {} : ", payment);
        return paymentService.getPaymentById(id);
    }
}
