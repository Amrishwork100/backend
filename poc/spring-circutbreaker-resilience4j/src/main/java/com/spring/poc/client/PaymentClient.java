package com.spring.poc.client;

import com.spring.poc.model.Payment;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Component
public class PaymentClient {

    private static final String PAYMENT_URL = "http://localhost:9090/api/payment/all";

    @Autowired
    private WebClient webClient;

    @CircuitBreaker(name = "spring-circutbreaker-resilience4j", fallbackMethod = "getPaymentHistoryFaultTolerance")
    public List<Payment> getPaymentHistory() {

            return webClient.get()
                    .uri(PAYMENT_URL)
                    .retrieve()
                    .bodyToFlux(Payment.class)
                    .collectList()
                    .block();

    }

    public List<Payment> getPaymentHistoryFaultTolerance(Exception ex) {
        System.out.println("Inside a Fault Tolerance method");
        return Stream.of(new Payment(getUUID(), 800.00, "12-07-2024"),
                        new Payment(getUUID(), 100.00, "12-07-2024"))
                .toList();

    }

    private String getUUID() {
        return String.valueOf(UUID.randomUUID());
    }
}