package com.spring.poc.model;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Payment {

    private String id;
    private double amount;
    private String paymentDate;

}
