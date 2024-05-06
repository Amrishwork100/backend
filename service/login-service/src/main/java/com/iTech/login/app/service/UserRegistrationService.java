package com.itech.login.app.service;

import com.itech.login.app.entity.UserRegistration;
import com.itech.login.app.model.JwtTokenResponse;
import com.itech.login.app.model.UserCredential;
import com.itech.login.app.repository.UserRegistrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRegistrationService {

    private final UserRegistrationRepository repository;

    private final JwtAuthenticationService authenticationService;

    public ResponseEntity<String> saveUser(UserRegistration userRegistration) {

        try {
            repository.save(userRegistration);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("User is not inserted in database {} ", e.getMessage());
        }

        return ResponseEntity.ok().build();
    }


    public ResponseEntity<JwtTokenResponse> fetchAndValidateUser(UserCredential user) {
        JwtTokenResponse response = new JwtTokenResponse();
        try {
            UserRegistration userRegistration = repository.findByEmail(user.getEmail());
            String authToken = authenticationService.generateToken(new HashMap<>(), user.getEmail());
            if (org.apache.commons.lang3.ObjectUtils.isNotEmpty(userRegistration)) {
                if (!userRegistration.getPassword().equals(user.getPassword())) {
                    throw new RuntimeException("user credential invalid");
                }
                response = JwtTokenResponse.builder()
                        .token(authToken)
                        .email(userRegistration.getUsername())
                        .build();
                return ResponseEntity.ok(response);
            }

        } catch (Exception e) {
            log.error("Error in fetch and validate user method ");
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<String> updateProfile(UserRegistration userRegistration) {
        UserRegistration registration = repository.findByEmail(userRegistration.getEmail());
        if (!registration.getUserFullName().equalsIgnoreCase(userRegistration.getUserFullName())) {
            registration.setUserFullName(userRegistration.getUserFullName());
        } else if (!registration.getEmail().equalsIgnoreCase(userRegistration.getEmail())) {
            registration.setEmail(userRegistration.getEmail());
        } else if (!registration.getMobile().equalsIgnoreCase(userRegistration.getMobile())) {
            registration.setMobile(userRegistration.getMobile());
        }
        return ResponseEntity.ok("profile updated");
    }
}
