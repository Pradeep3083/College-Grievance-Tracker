package com.example.CGT.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        org.springframework.security.core.AuthenticationException exception)
            throws IOException, ServletException {

        if (exception instanceof UsernameNotFoundException) {
            response.sendRedirect("/login?error=user_not_found");
        } else if (exception instanceof BadCredentialsException) {
            response.sendRedirect("/login?error=invalid");
        } else {
            response.sendRedirect("/login?error=unknown");
        }
    }
}
