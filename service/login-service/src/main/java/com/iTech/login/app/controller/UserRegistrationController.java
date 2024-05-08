package com.itech.login.app.controller;

import com.itech.login.app.entity.UserData;
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
    public ResponseEntity<String> saveUser(@RequestBody UserData userData) {
        service.saveUser(userData);
        return ResponseEntity.ok().build();

    }

    @PostMapping("/login")
    public ResponseEntity<JwtTokenResponse> loginUser(@RequestBody UserCredential userCredential) {
        return service.fetchAndValidateUser(userCredential);
    }

    @PutMapping("/profile/update")
    public ResponseEntity<String> updateUserProfile(@RequestBody UserData userData){
        return service.updateProfile(userData);
    }
}
