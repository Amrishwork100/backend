package com.itech.springsecurity.section4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoansController {

    @GetMapping("/myLoans")
    public String getAccountDetails(){
        return " Here is my contact details from DB";
    }
}
