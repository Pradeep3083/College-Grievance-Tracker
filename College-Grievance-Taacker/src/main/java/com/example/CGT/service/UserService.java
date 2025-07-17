package com.example.CGT.service;

import com.example.CGT.dto.UserDto;
import com.example.CGT.entity.User;

import java.util.List;

public interface UserService {
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto>findAllUsers();

    List<User> getAllUsersSortedDesc();
}
