package com.example.customerproductsystem.admin.dto;
import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.entity.AdminRole;
import java.time.LocalDateTime;

public record AdminSignupResponse(
        Long id,
        String name,
        String email,
        AdminRole status,
        com.example.customerproductsystem.admin.entity.AdminStatus adminStatus, LocalDateTime createAt
) {
    public static AdminSignupResponse from(Admin admin) {
        return new AdminSignupResponse(
                admin.getId(),
                admin.getName(),
                admin.getEmail(),
                admin.getRole(),
                admin.getStatus(),
                admin.getCreatedAt()
        );
    }
}
