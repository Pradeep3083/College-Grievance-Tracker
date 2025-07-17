package com.example.CGT.service;

import com.example.CGT.entity.Role;
import com.example.CGT.entity.User;
import com.example.CGT.repository.RoleRepository;
import com.example.CGT.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminSetupService implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User admin = userRepository.findByEmail("admin@admin.com");
        if (admin == null) {
            admin = new User();
            admin.setName("Admin");
            admin.setEmail("admin@admin.com");
            admin.setPassword(passwordEncoder.encode("admin"));
        }

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
        }

        if (admin.getRoles() == null || admin.getRoles().isEmpty()) {
            admin.setRoles(List.of(adminRole));
            userRepository.save(admin);
            System.out.println("✅ Admin user role assigned.");
        } else {
            System.out.println("✅ Admin user already has a role.");
        }
    }
}
