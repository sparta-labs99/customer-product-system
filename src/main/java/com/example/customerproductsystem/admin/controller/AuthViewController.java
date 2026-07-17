package com.example.customerproductsystem.admin.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AuthViewController {

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "auth/signup";
    }

    @GetMapping("/profile")
    public String profilePage() {return "admin/profile";}

    @GetMapping("/view/admins")
    public String adminListPage() {return "admin/list";}

    @GetMapping("/view/admins/{adminId}")
    public String adminDetailPage(@PathVariable Long adminId) {
        return "admin/detail";
    }
}
