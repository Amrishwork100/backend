package com.itech.springsecurity.section1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecureController {

    @GetMapping("/welcome")
    public String get() {
        return "Welcome to spring security";
    }
}
