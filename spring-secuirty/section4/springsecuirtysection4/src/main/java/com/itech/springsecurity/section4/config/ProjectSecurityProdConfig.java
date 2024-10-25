package com.itech.springsecurity.section4.config;

import com.itech.springsecurity.section4.exception.CustomAccessDeniedHandler;
import com.itech.springsecurity.section4.exception.CustomBasicAuthenticationEntryPoint;
import com.itech.springsecurity.section4.filter.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.password.HaveIBeenPwnedRestApiPasswordChecker;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
import java.util.List;

@Configuration
@Profile("prod")
public class ProjectSecurityProdConfig {
    public static final String UI_URL = "http://localhost:3000";

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http//.securityContext(security-> security.requireExplicitSave(false))
                .cors(corsConfigure -> corsConfigure.configurationSource(new CorsConfigurationSource() {
                    @Override // it's required to implement when front app wants to communicate with backend service.
                    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                        CorsConfiguration config = new CorsConfiguration();
                        config.setAllowedOrigins(Collections.singletonList(UI_URL));
                        config.setAllowedMethods(Collections.singletonList("*"));
                        config.setAllowedHeaders(Collections.singletonList("*"));
                        config.setExposedHeaders(List.of("Authorization"));
                        config.setAllowCredentials(true);
                        config.setMaxAge(3600L);
                        return config;
                    }
                }))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // using this snippet code always able to generate JSESSIONID
                /*.sessionManagement(session -> session
                        .sessionFixation(SessionManagementConfigurer.SessionFixationConfigurer::newSession)
                        .invalidSessionUrl("/invalidSession").maximumSessions(3)
                        .maxSessionsPreventsLogin(true))
                */
                //.addFilterBefore(new RequestValidationBeforeFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new AuthoritiesLoggingAfterFilter(), BasicAuthenticationFilter.class)
                .addFilterAt(new AuthoritiesLoggingAtFilter(), BasicAuthenticationFilter.class)
                .addFilterAfter(new JWTTokenGeneratorFilter(), BasicAuthenticationFilter.class)
                .addFilterBefore(new JWTTokenValidatorFilter(), BasicAuthenticationFilter.class)
                .requiresChannel(rcc -> rcc.anyRequest().requiresInsecure()) // only allow HTTPS
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        /*.requestMatchers("/myAccount","/account").hasAuthority("VIEWACCOUNT")
                        .requestMatchers("/myLoans","loans").hasAnyAuthority("VIEWLOANS","VIEWACCOUNT")
                        .requestMatchers("/myCards","cards").hasAuthority("VIEWCARDS")
                        .requestMatchers("/myBalance","/transaction").hasAuthority("VIEWBALANCE")
                        .requestMatchers("/user").authenticated()*/
                        .requestMatchers("/myAccount", "/account").hasRole("USER")
                        .requestMatchers("/myLoans", "loans").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/myCards", "cards").hasRole("USER")
                        .requestMatchers("/myBalance", "/transaction").hasRole("USER")
                        .requestMatchers("/user").authenticated()
                        .requestMatchers("/contact", "/notice", "/error", "/register", "invalidSession", "/new-register").permitAll());
        //http.formLogin(Customizer.withDefaults());
        http.httpBasic(bac -> bac.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(adc -> adc.accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
    }
    // it's to create user details in application memory by using below code
  /*  @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
       return new JdbcUserDetailsManager(dataSource);
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // it's used to check whether user enter password is strong or not by the below code
    // if password is not strong, then the framework will throw an exception/error to the user
    // it is a production level code
    @Bean
    public CompromisedPasswordChecker compromisedPasswordChecker() {
        return new HaveIBeenPwnedRestApiPasswordChecker();
    }
}
