package com.itech.springsecurity.section4.config;


import com.itech.springsecurity.section4.repository.CustomerRepository;
import com.itech.springsecurity.section4.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EazyBankUserDetailService implements UserDetailsService {
    @Autowired
    private  CustomerRepository repository;


    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Customer customer = repository.findByEmail(email).orElseThrow(() -> new
                UsernameNotFoundException("User details not found for the user: " + email));
        List<SimpleGrantedAuthority> authorities = customer.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName()))
                .toList();
        return new User(customer.getEmail(), customer.getPwd(), authorities);
    }
}
