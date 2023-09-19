package com.mycompany.controller;

import com.mycompany.model.User;
import com.mycompany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ReportController {

    private final UserService services;

    @Autowired
    public ReportController(UserService services) {
        this.services = services;
    }

    @GetMapping("/report")
    public String getReport(Model model){
        List<User> users = services.ListAll();
        model.addAttribute("users", users);
        return "report";
    }

    @PostMapping("/report")
    public String viewReport(@RequestParam("keyword") String keyword, Model model){
        List<User> searchResults = services.findByKeyword(keyword);
        model.addAttribute("users", searchResults);
        return "report";
    }
}
