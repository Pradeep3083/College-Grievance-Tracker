package com.example.CGT.service.impl;

import com.example.CGT.dto.UserDto;
import com.example.CGT.entity.Role;
import com.example.CGT.entity.User;
import com.example.CGT.repository.RoleRepository;
import com.example.CGT.repository.UserRepository;
import com.example.CGT.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void saveUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

        user.setRole("ROLE_STUDENT");

        Role role = roleRepository.findByName("ROLE_STUDENT");
        if (role == null) {
            role = createRole("ROLE_STUDENT");
        }

        user.setRoles(Arrays.asList(role)); // Add to join table
        userRepository.save(user);
    }




    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId());
            userDto.setName(user.getName());
            userDto.setEmail(user.getEmail());
            userDtos.add(userDto);
        }
        return userDtos;
    }

    private Role createRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        return roleRepository.save(role);
    }

    public List<User> getAllUsersSortedDesc() {
        return userRepository.findAllByOrderByCreatedAtDesc();
    }

}
