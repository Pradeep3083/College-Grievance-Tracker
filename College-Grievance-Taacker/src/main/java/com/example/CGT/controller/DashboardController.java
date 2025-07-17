package com.example.CGT.controller;

import com.example.CGT.entity.Grievance;
import com.example.CGT.entity.User;
import com.example.CGT.service.GrievanceService;
import com.example.CGT.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DashboardController {

    private final GrievanceService grievanceService;
    private final UserService userService;

    public DashboardController(GrievanceService grievanceService, UserService userService) {
        this.grievanceService = grievanceService;
        this.userService = userService;
    }

    @GetMapping("/student-dashboard")
    public String showStudentDashboard(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Get the logged-in user's email

        User student = userService.findByEmail(email);
        if (student == null) {
            return "error"; // Handle user not found
        }

        model.addAttribute("student", student); // Add student object to the model
        List<Grievance> grievances = grievanceService.getGrievancesByStudent(student);
        model.addAttribute("grievances", grievances); // Add grievances to the model
        model.addAttribute("newGrievance", new Grievance()); // Add a new Grievance object to the model

        return "student-dashboard"; // Return student-dashboard view
    }



    @PostMapping("/submit-dashboard")
    public String submitGrievance(@ModelAttribute("newGrievance") Grievance grievance) {
        // Get the currently logged-in user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName(); // Get the logged-in user's email
        User student = userService.findByEmail(email); // Fetch the student details using the email

        // Handle grievance submission logic
        grievanceService.submitGrievance(grievance, student); // Pass the student object to the service
        return "redirect:/submit-dashboard"; // Redirect after submission
    }
}
