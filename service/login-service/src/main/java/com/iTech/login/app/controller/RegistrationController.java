package com.itech.login.app.controller;

import com.itech.login.app.entity.UserRegistration;
import com.itech.login.app.model.TokenResponse;
import com.itech.login.app.service.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login/api")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserRegistrationService service;

    @PostMapping("/register")
    public ResponseEntity<TokenResponse> userRegistration(@RequestBody UserRegistration userRegistration) {
        return service.createUser(userRegistration);
    }
}
