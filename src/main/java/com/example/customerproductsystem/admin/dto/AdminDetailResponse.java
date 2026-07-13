package com.example.customerproductsystem.admin.dto;

import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.entity.AdminRole;
import com.example.customerproductsystem.admin.entity.AdminStatus;
import java.time.LocalDateTime;

public record AdminDetailResponse(
        Long id,
        String name,
        String email,
        String phoneNumber,
        AdminRole role,
        AdminStatus status,
        LocalDateTime createdAt,
        LocalDateTime approvedAt,
        LocalDateTime rejectedAt,
        String rejectionReason
) {

    public static AdminDetailResponse from(
            Admin admin
    ) {
        return new AdminDetailResponse(
                admin.getId(),
                admin.getName(),
                admin.getEmail(),
                admin.getPhoneNumber(),
                admin.getRole(),
                admin.getStatus(),
                admin.getCreatedAt(),
                admin.getApprovedAt(),
                admin.getRejectedAt(),
                admin.getRejectionReason()
        );
    }
}