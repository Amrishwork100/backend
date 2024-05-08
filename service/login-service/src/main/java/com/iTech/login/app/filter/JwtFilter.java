package com.itech.login.app.filter;

import com.itech.login.app.service.JwtAuthenticationService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.lang.reflect.MalformedParametersException;

@Component
@Slf4j
public class JwtFilter extends OncePerRequestFilter {
    @Autowired
    private JwtAuthenticationService jwtAuthenticationService;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        try {
            String header = response.getHeader("Authorization");
            if (header != null && header.contains("Bearer ")) {
                String authToken = header.substring(7);
                boolean isTokenValid = jwtAuthenticationService.isValidToken(authToken);
                if (isTokenValid && SecurityContextHolder.getContext().getAuthentication() == null) {
                    String userEmail = jwtAuthenticationService.extractUserName(authToken);
                    if (ObjectUtils.isNotEmpty(userEmail)) {
                        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userEmail, userDetails, null);
                        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                    }
                }

            }
        } catch (UsernameNotFoundException u) {
            log.error("User not found : " + u.getMessage());
            u.printStackTrace();
        } catch (MalformedParametersException m) {
            log.error("error in auth token not found : " + m.getMessage());
        }

        filterChain.doFilter(request, response);
    }
}
