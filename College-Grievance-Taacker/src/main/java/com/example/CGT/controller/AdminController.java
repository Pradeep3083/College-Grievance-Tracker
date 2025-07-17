package com.example.CGT.controller;

import com.example.CGT.dto.UserDto;
import com.example.CGT.entity.Grievance;
import com.example.CGT.entity.User;
import com.example.CGT.service.GrievanceService;
import com.example.CGT.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class AdminController {

    private final GrievanceService grievanceService;
    private final UserService userService;

    public AdminController(GrievanceService grievanceService, UserService userService) {
        this.grievanceService = grievanceService;
        this.userService = userService;
    }

    @GetMapping("/admin-dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAdminDashboard(Model model) {
        model.addAttribute("grievances", grievanceService.getAllGrievancesSortedDesc());
        model.addAttribute("users", userService.getAllUsersSortedDesc());
        return "admin-dashboard"; // This should match your Thymeleaf file name
    }


}
