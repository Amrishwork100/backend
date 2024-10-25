package com.itech.springsecurity.section4.filter;

import com.itech.springsecurity.section4.constant.ApplicationConstant;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

import static com.itech.springsecurity.section4.constant.ApplicationConstant.JWT_SECRET_DEFAULT_VALUE;

@Slf4j
@Component
public class JWTTokenGeneratorFilter extends OncePerRequestFilter {

    /**
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (null != authentication) {
            Environment env = getEnvironment();
            String secret = env.getProperty(ApplicationConstant.JWT_SECRET_KEY,
                    JWT_SECRET_DEFAULT_VALUE);
            SecretKey key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));

            String jwtToken = Jwts.builder()
                    .issuer("Eazy Bank").subject("JWT Token")
                    .issuedAt(new Date())
                    .claim("username", authentication.getName())
                    .claim("authorities", authentication.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.joining(",")))
                    .expiration(new Date(new Date().getTime() + 3600000))
                    .signWith(key)
                    .compact();
            log.info("Generated JWT Token: {}", jwtToken);
            response.setHeader(ApplicationConstant.JWT_HEADER, jwtToken);
        }
        filterChain.doFilter(request, response);
    }

    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        return !request.getRequestURI().equalsIgnoreCase("/user");
    }
}
