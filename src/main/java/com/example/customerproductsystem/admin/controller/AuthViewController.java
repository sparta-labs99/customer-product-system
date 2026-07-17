package com.example.customerproductsystem.admin.controller;
import com.example.customerproductsystem.auth.LoginAdmin;
import com.example.customerproductsystem.auth.SessionConst;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping("/view")
public class AuthViewController {

    @GetMapping("/login")
    public String loginPage(@SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) LoginAdmin loginAdmin) {
        if (loginAdmin != null) {
            return "redirect:/view/dashboard";
        }
        return "auth/login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "auth/signup";
    }

    @GetMapping("/profile")
    public String profilePage() {return "admin/profile";}

    @GetMapping("/admins")
    public String adminListPage() {return "admin/list";}

    @GetMapping("/admins/{adminId}")
    public String adminDetailPage(@PathVariable Long adminId) {
        return "admin/detail";
    }
}
