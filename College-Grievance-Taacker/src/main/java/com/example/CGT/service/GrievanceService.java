package com.example.CGT.service;

import com.example.CGT.entity.Grievance;
import com.example.CGT.entity.User;

import java.util.List;

public interface GrievanceService {
    void submitGrievance(Grievance grievance, User student);
    List<Grievance> getGrievancesByStudent(User student);
    List<Grievance> getAllGrievances();
    void updateGrievanceStatus(Long grievanceId, String status);

    List<Grievance> getAllGrievancesSortedDesc();
}
