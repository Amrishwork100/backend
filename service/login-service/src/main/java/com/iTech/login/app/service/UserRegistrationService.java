package com.itech.login.app.service;

import com.itech.login.app.constant.AppConstants;
import com.itech.login.app.entity.UserRegistration;
import com.itech.login.app.model.TokenResponse;
import com.itech.login.app.repository.UserRegistrationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.HashMap;
import java.util.function.Predicate;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserRegistrationService {

    private final UserRegistrationRepository repository;

    private final JwtAuthenticationService authenticationService;

    public ResponseEntity<TokenResponse> createUser(UserRegistration userRegistration) {
        String authToken = null;
        UserRegistration user = null;
        try {
            user = repository.save(userRegistration);
            authToken = authenticationService.generateToken(new HashMap<>(), user.getEmail());
        } catch (Exception e) {
            e.printStackTrace();
            log.error("User is not inserted in database {} ", e.getMessage());
        }
        assert user != null;
        TokenResponse tokenResponse = TokenResponse.builder()
                .token((!ObjectUtils.isEmpty(authToken))
                        ? authToken : AppConstants.EMPTY)
                .email(user.getEmail())
                .build();
        return ResponseEntity.ok(tokenResponse);
    }


}
