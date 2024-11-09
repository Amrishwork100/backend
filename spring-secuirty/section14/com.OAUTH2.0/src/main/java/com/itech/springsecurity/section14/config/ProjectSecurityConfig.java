package com.itech.springsecurity.section14.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(request ->
                        request.requestMatchers("/secure").authenticated()
                                .anyRequest().permitAll())
                .formLogin(Customizer.withDefaults())
                .oauth2Login(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        ClientRegistration gitHub = getGitHub();
        ClientRegistration faceBook = getFaceBook();
        return new InMemoryClientRegistrationRepository(gitHub, faceBook);
    }

    private ClientRegistration getGitHub() {
        return CommonOAuth2Provider.GITHUB.getBuilder("github").clientId("Ov23liGdfMTT6iTpNRM1")
                .clientSecret("3e382aa6df67a33d153c6661816f0361d967c420").build();
    }

    private ClientRegistration getFaceBook() {
        return CommonOAuth2Provider.FACEBOOK.getBuilder("facebook").clientId("520229264155053")
                .clientSecret("7b9657c7b7dbea94a85b5ccfdfa69431").build();
    }
}
