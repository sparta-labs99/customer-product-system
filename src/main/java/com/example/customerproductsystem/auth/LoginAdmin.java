package com.example.customerproductsystem.auth;

import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.entity.AdminRole;

import java.io.Serializable;

public record LoginAdmin(Long id, String email, AdminRole role) implements Serializable {

    public static LoginAdmin from(Admin admin) {
        return new LoginAdmin(
                admin.getId(),
                admin.getEmail(),
                admin.getRole()
        );
    }
}
