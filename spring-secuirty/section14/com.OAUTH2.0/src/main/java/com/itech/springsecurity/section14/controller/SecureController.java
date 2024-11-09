package com.itech.springsecurity.section14.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecureController {

    @GetMapping
    public String secureApi(){
        return "secure.html";
    }
}
