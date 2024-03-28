package com.spring.poc.client;

import com.spring.poc.model.Payment;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

@Component
@Log4j2
public class PaymentClient {

    private static final String PAYMENT_URL = "http://localhost:9090/api/payment/all";

    @Autowired
    private WebClient webClient;

    int attempts = 1;

    @CircuitBreaker(name = "spring-circutbreaker-resilience4j", fallbackMethod = "getPaymentHistoryFallback")
    public List<Payment> getPaymentHistory() {
        log.info("circuit breaker impl " + LocalDateTime.now());
        return webClient.get()
                .uri(PAYMENT_URL)
                .retrieve()
                .bodyToFlux(Payment.class)
                .collectList()
                .block();

    }

    public List<Payment> getPaymentHistoryFallback(Exception ex) {
        log.info("Inside a Circuit Breaker Fault Tolerance method");
        return Stream.of(new Payment(getUUID(), "Dell Laptop", 80000.00, "12-03-2024"),
                        new Payment(getUUID(), "Mac laptop", 100000.00, "12-03-2024"))
                .toList();

    }

    @Retry(name = "spring-circutbreaker-resilience4j", fallbackMethod = "getRetryPaymentHistoryFallback")
    public List<Payment> getRetryPaymentHistory() {
        log.info("Number of attempts: " + attempts + " and time stamp : " + LocalDateTime.now());
        attempts++;
        return webClient.get()
                .uri(PAYMENT_URL)
                .retrieve()
                .bodyToFlux(Payment.class)
                .collectList()
                .block();

    }

    public List<Payment> getRetryPaymentHistoryFallback(Exception ex) {
        log.info("Inside a Retry Fault Tolerance method " + LocalDateTime.now());
        return Stream.of(new Payment(getUUID(), "Dell Laptop",2000.00, "12-07-2024"),
                        new Payment(getUUID(),"Lenovo Laptop", 1500.00, "12-07-2024"))
                .toList();

    }

    private String getUUID() {
        return String.valueOf(UUID.randomUUID());
    }
}