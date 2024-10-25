package com.itech.springsecurity.section4.controller;

import com.itech.springsecurity.section4.repository.AccountsRepository;
import com.itech.springsecurity.section4.model.Accounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AccountController {
    @Autowired
    private AccountsRepository accountsRepository;

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam long id) {
        Accounts accounts = accountsRepository.findByCustomerId(id);
        if (accounts != null) {
            return accounts;
        } else {
            return null;
        }
    }

    @GetMapping("/account")
    public List<Accounts> getAllAccountDetails() {
        return (List<Accounts>) accountsRepository.findAll();

    }
}
