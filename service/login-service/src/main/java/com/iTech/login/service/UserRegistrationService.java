package com.itech.login.service;

import com.itech.login.entity.UserRegisterRequest;
import com.itech.login.model.UserWithTokenResponse;
import com.itech.login.entity.UserLoginRequest;
import com.itech.login.repository.UserRegistrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRegistrationService {

    private final UserRegistrationRepository repository;

    private final JwtAuthenticationService authenticationService;

    public ResponseEntity<String> saveUser(UserRegisterRequest userData) {
        UserRegisterRequest data = new UserRegisterRequest();
        try {
            userData.setId(getTransactionId(userData.getEmail()));
            data = repository.save(userData);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("User is not inserted in database {} ", e.getMessage());
        }

        return ResponseEntity.ok(data.getId());
    }

    private String getTransactionId(String email) {
        return UUID.randomUUID().toString();
    }


    public ResponseEntity<UserWithTokenResponse> fetchAndValidateUser(UserLoginRequest user) {
        UserWithTokenResponse response = new UserWithTokenResponse();
        try {
            UserRegisterRequest userData = repository.findByEmail(user.getEmail());
            String authToken = authenticationService.generateToken(new HashMap<>(), user.getEmail());
            if (org.apache.commons.lang3.ObjectUtils.isNotEmpty(userData)) {
                if ((!userData.getEmail().equals(user.getEmail())) || (!userData.getPassword().equals(user.getPassword()))) {
                    throw new RuntimeException("invalid credential");
                }
                response = UserWithTokenResponse
                        .builder()
                        .id(userData.getId())
                        .firstName(userData.getFirstName())
                        .lastName(userData.getLastName())
                        .email(userData.getUsername())
                        .password(userData.getPassword())
                        .mobile(userData.getMobile())
                        .token(authToken)
                        .build();
                return ResponseEntity.ok(response);
            }

        } catch (Exception e) {
            log.error("Error in fetch and validate user method ");
        }
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<String> updateProfile(UserRegisterRequest userData) {
        UserRegisterRequest registration = repository.findByEmail(userData.getEmail());
        if (!registration.getFirstName().equalsIgnoreCase(userData.getFirstName())) {
            registration.setFirstName(userData.getFirstName());
        } else if (!registration.getEmail().equalsIgnoreCase(userData.getEmail())) {
            registration.setEmail(userData.getEmail());
        } else if (!registration.getMobile().equalsIgnoreCase(userData.getMobile())) {
            registration.setMobile(userData.getMobile());
        }
        return ResponseEntity.ok("profile updated");
    }
}
