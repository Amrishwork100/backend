package com.paymentservice.service;

import com.paymentservice.entity.Payment;
import com.paymentservice.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getPaymentHistory() {
        return paymentRepository.findAll();
    }

    public Payment createPayment(Payment payment) {
        payment.setId(getUUID());
        return paymentRepository.save(payment);
    }

    private String getUUID() {
        return String.valueOf(UUID.randomUUID());
    }

    public Payment getPaymentById(String id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        return optionalPayment.get();
    }
}
