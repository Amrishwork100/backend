package com.itech.springsecurity.section4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

    @GetMapping("/contact")
    public String getAccountDetails(){
        return " Here is my contact details from DB";
    }
}
