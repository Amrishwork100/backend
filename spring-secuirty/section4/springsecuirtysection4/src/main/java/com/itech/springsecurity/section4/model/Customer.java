package com.itech.springsecurity.section4.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "email")
    private String email;
    @Column(name = "pwd")
    private  String pwd;
    @Column(name = "role")
    private String role;
}
