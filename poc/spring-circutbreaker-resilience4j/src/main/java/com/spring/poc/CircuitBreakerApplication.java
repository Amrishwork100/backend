package com.spring.poc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
public class CircuitBreakerApplication {

	public static void main(String[] args) {
		SpringApplication.run(CircuitBreakerApplication.class, args);
	}

}
