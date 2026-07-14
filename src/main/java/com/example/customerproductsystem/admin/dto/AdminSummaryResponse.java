package com.example.customerproductsystem.admin.dto;

import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.entity.AdminRole;
import com.example.customerproductsystem.admin.entity.AdminStatus;

import java.time.LocalDateTime;

public record AdminSummaryResponse(
        Long id,
        String name,
        String email,
        String phoneNumber,
        AdminRole role,
        AdminStatus status,
        LocalDateTime createdAt,
        LocalDateTime approvedAt
) {

    public static AdminSummaryResponse from(Admin admin) {
        return new AdminSummaryResponse(
                admin.getId(),
                admin.getName(),
                admin.getEmail(),
                admin.getPhoneNumber(),
                admin.getRole(),
                admin.getStatus(),
                admin.getCreatedAt(),
                admin.getApprovedAt()
        );
    }
}