package com.example.CGT.repository;

import com.example.CGT.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
    List<User> findAllByOrderByCreatedAtDesc();

}
