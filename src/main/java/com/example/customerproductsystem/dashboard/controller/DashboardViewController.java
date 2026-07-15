package com.example.customerproductsystem.dashboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/dashboard")
public class DashboardViewController {

    @GetMapping
    public String dashboardMainPage() {
        return "dashboard/main";
    }

}
