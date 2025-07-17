package com.example.CGT.service.impl;

import com.example.CGT.entity.Grievance;
import com.example.CGT.entity.User;
import com.example.CGT.repository.GrievanceRepository;
import com.example.CGT.service.GrievanceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrievanceServiceImpl implements GrievanceService {

    private final GrievanceRepository grievanceRepository;

    public GrievanceServiceImpl(GrievanceRepository grievanceRepository) {
        this.grievanceRepository = grievanceRepository;
    }

    @Override
    public void submitGrievance(Grievance grievance, User student) {
        grievance.setStudent(student);
        grievanceRepository.save(grievance);
    }

    @Override
    public List<Grievance> getGrievancesByStudent(User student) {
        return grievanceRepository.findByStudentId(student.getId());
    }

    @Override
    public List<Grievance> getAllGrievances() {
        return grievanceRepository.findAll();
    }

    @Override
    public void updateGrievanceStatus(Long grievanceId, String status) {
        Grievance grievance = grievanceRepository.findById(grievanceId).orElseThrow();
        grievance.setStatus(status);
        grievanceRepository.save(grievance);
    }

    public List<Grievance> getAllGrievancesSortedDesc() {
        return grievanceRepository.findAllByOrderByCreatedAtDesc();
    }

}
