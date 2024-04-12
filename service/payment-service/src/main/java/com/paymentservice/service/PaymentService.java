package com.paymentservice.service;

import com.paymentservice.entity.Payment;
import com.paymentservice.repository.PaymentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    public List<Payment> getPaymentHistory() {
        List<Payment> payments = paymentRepository.findAll();
        payments.forEach(payment -> {
            log.info(" Fetch all payment info {} : ", payment);
        });
        return payments;
    }

    public Payment createPayment(Payment payment) {
        payment.setId(getUUID());
        Payment pay = paymentRepository.save(payment);
        log.info(" Accept the payment from users {} : ", pay);
        return pay;
    }

    private String getUUID() {
        return String.valueOf(UUID.randomUUID());
    }

    public Payment getPaymentById(String id) {
        Optional<Payment> optionalPayment = paymentRepository.findById(id);
        log.info(" Fetch all payment info {} : ",optionalPayment);
        return optionalPayment.orElse(null);
    }
}
