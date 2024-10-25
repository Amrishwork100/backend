package com.itech.springsecurity.section4.filter;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
@Slf4j
public class AuthoritiesLoggingAfterFilter implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("AuthoritiesLoggingAfterFilter executed at basic authentication filter");
        chain.doFilter(request,response);
    }
}
