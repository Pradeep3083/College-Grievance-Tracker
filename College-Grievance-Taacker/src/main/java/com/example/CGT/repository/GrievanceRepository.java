package com.example.CGT.repository;

import com.example.CGT.entity.Grievance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrievanceRepository extends JpaRepository<Grievance, Long> {
    List<Grievance> findByStudentId(Long studentId);
    List<Grievance> findAllByOrderByCreatedAtDesc();
}
