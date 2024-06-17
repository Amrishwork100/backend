package com.itech.login.controller;

import com.itech.login.entity.UserLoginRequest;
import com.itech.login.entity.UserRegisterRequest;
import com.itech.login.model.UserWithTokenResponse;
import com.itech.login.service.UserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/v1/api")
@CrossOrigin
@Slf4j
public class UserRegistrationController {

    @Autowired
    private  UserRegistrationService userRegistrationService;

    @PostMapping("/register")
    public ResponseEntity<String> saveUser(@RequestBody UserRegisterRequest userRegisterRequest) {
        return userRegistrationService.saveUser(userRegisterRequest);

    }

    @PostMapping("/login")
    public UserWithTokenResponse loginUser(@RequestBody UserLoginRequest userLoginRequest) {
        UserWithTokenResponse response = userRegistrationService.fetchAndValidateUser(userLoginRequest).getBody();
        log.info("response body ......" + response);
        return response;
    }

    @PutMapping("/profile/update")
    public ResponseEntity<String> updateUserProfile(@RequestBody UserRegisterRequest userData){
        return userRegistrationService.updateProfile(userData);
    }
}
