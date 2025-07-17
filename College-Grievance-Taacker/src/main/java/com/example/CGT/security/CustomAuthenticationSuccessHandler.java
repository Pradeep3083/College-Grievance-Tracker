package com.example.CGT.security;

import com.example.CGT.entity.User;
import com.example.CGT.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        String email = authentication.getName();
        String selectedRole = request.getParameter("role"); // ADMIN or STUDENT

        if (selectedRole == null || selectedRole.trim().isEmpty()) {
            response.sendRedirect("/login?error=role_not_selected");
            return;
        }

        User user = userRepository.findByEmail(email);
        if (user == null) {
            response.sendRedirect("/login?error=user_not_found");
            return;
        }

        String expectedRole = "ROLE_" + selectedRole.toUpperCase();

        boolean hasExpectedRole = user.getRoles().stream()
                .anyMatch(role -> role.getName().equalsIgnoreCase(expectedRole));

        if (!hasExpectedRole) {
            response.sendRedirect("/login?error=role_mismatch");
            return;
        }

        // Redirect based on selected role
        switch (selectedRole.toUpperCase()) {
            case "ADMIN":
                response.sendRedirect("/admin-dashboard");
                break;
            case "STUDENT":
                response.sendRedirect("/student-dashboard");
                break;
            default:
                response.sendRedirect("/login?error=unknown_role");
        }
    }
}
