package com.example.customerproductsystem.admin.dto;

import com.example.customerproductsystem.admin.entity.AdminRole;
import com.example.customerproductsystem.auth.LoginAdmin;

public record AdminLoginResponse(
        Long id,
        String email,
        AdminRole role
) {
    public static AdminLoginResponse from(LoginAdmin loginAdmin) {
        return new AdminLoginResponse(
                loginAdmin.id(),
                loginAdmin.email(),
                loginAdmin.role()
        );
    }
}
