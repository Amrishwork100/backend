package com.itech.springsecurity.section4.controller;

import com.itech.springsecurity.section4.repository.LoanRepository;
import com.itech.springsecurity.section4.model.Loans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LoansController {
    @Autowired
    private LoanRepository loanRepository;

    @GetMapping("/myLoans")
    @PreAuthorize("hasRole('USER')")
    @PreFilter("filterObject.loanType!= Test")
    @PostFilter("filterObject.loanType!= Test")
    public ResponseEntity<List<Loans>> getLoanDetails(@RequestParam long id, @RequestBody List<String> loanTypes) {
        List<Loans> loans = loanRepository.findByCustomerIdOrderByStartDtDesc(id);
        if (!loans.isEmpty()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(loans);
        } else {
            return null;
        }
    }

    @GetMapping("/loans")
    public List<Loans> getAllLoansDetails() {
        return (List<Loans>) loanRepository.findAll();

    }

}
