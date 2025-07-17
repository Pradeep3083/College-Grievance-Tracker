package com.example.CGT.controller;

import com.example.CGT.entity.Grievance;
import com.example.CGT.entity.User;
import com.example.CGT.service.GrievanceService;
import com.example.CGT.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/grievances")
public class GrievanceController {

    private final GrievanceService grievanceService;
    private final UserService userService;

    public GrievanceController(GrievanceService grievanceService, UserService userService) {
        this.grievanceService = grievanceService;
        this.userService = userService;
    }

    @GetMapping("/student")
    @PreAuthorize("hasRole('STUDENT')")
    public String studentDashboard(Model model, Principal principal) {
        User student = userService.findByEmail(principal.getName());
        List<Grievance> grievances = grievanceService.getGrievancesByStudent(student);
        model.addAttribute("grievances", grievances);
        model.addAttribute("newGrievance", new Grievance());
        return "student-dashboard";
    }

    @PostMapping("/submit")
    public String submitGrievance(@ModelAttribute("newGrievance") Grievance grievance, Principal principal) {
        User student = userService.findByEmail(principal.getName());
        grievanceService.submitGrievance(grievance, student);
        return "redirect:/student-dashboard";
    }

    @GetMapping("/admin")
    public String adminDashboard(Model model) {
        List<Grievance> grievances = grievanceService.getAllGrievances();
        model.addAttribute("grievances", grievances);
        return "admin-dashboard";
    }

    @PostMapping("/update")
    public String updateGrievanceStatus(@RequestParam("id") Long id, @RequestParam("status") String status) {
        grievanceService.updateGrievanceStatus(id, status);
        return "redirect:/grievances/admin";
    }
}
