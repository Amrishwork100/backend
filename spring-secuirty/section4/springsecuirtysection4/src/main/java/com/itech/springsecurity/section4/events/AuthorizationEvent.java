package com.itech.springsecurity.section4.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.AuthorizationDeniedException;

@Slf4j
public class AuthorizationEvent {
    @EventListener
    public void authorizatonOnFailure(AuthorizationDeniedException authorizationDeniedException){
        log.error("Authorization due to not valid privileges "+ authorizationDeniedException.getAuthorizationResult()
                .isGranted());
    }
}
