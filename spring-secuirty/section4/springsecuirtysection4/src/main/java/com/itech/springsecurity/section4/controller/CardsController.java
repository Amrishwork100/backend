package com.itech.springsecurity.section4.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CardsController {

    @GetMapping("/myCards")
    public String getAccountDetails(){
        return " Here is my cards details from DB";
    }
}
