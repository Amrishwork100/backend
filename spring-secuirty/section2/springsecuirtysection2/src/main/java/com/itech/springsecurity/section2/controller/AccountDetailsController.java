package com.itech.springsecurity.section2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountDetailsController {

    @GetMapping("/myAccount")
    public String getAccountDetails(){
        return " Here is the Account details from DB";
    }
}
