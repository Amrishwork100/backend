package com.itech.login.app.controller;

import com.itech.login.app.entity.UserRegistration;
import com.itech.login.app.model.JwtTokenResponse;
import com.itech.login.app.model.UserCredential;
import com.itech.login.app.service.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/v1/api")
@RequiredArgsConstructor
public class UserRegistrationController {

    private final UserRegistrationService service;

    @PostMapping("/register")
    public ResponseEntity<String> userRegistration(@RequestBody UserRegistration userRegistration) {
        service.saveUser(userRegistration);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> userLogin(@RequestBody UserCredential userCredential) {
        return service.fetchAndValidateUser(userCredential);
    }

    @PutMapping("/profile/update")
    public ResponseEntity<String> updateProfile(@RequestBody UserRegistration userRegistration){
        return service.updateProfile(userRegistration);
    }
}
