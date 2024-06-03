package com.itech.login.app.controller;

import com.itech.login.app.entity.UserData;
import com.itech.login.app.model.JwtTokenResponse;
import com.itech.login.app.model.UserCredential;
import com.itech.login.app.service.UserRegistrationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/v1/api")
@RequiredArgsConstructor
@CrossOrigin
@Slf4j
public class UserRegistrationController {

    private final UserRegistrationService service;

    @PostMapping("/register")
    public ResponseEntity<String> saveUser(@RequestBody UserData userData) {
        service.saveUser(userData);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/login")
    public JwtTokenResponse loginUser(@RequestBody UserCredential userCredential) {
        JwtTokenResponse response = service.fetchAndValidateUser(userCredential).getBody();
        log.info("response body ......" + response);
        return response;
    }

    @PutMapping("/profile/update")
    public ResponseEntity<String> updateUserProfile(@RequestBody UserData userData){
        return service.updateProfile(userData);
    }
}
