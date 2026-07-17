package com.example.customerproductsystem.admin.dto;

import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.entity.AdminRole;

import java.time.LocalDateTime;

public record MyProfileResponse(
        Long id,
        String name,
        String phoneNumber,
        AdminRole role,
        LocalDateTime approvedAt

) {
    public static MyProfileResponse from(Admin admin) {
        return new MyProfileResponse(
                admin.getId(),
                admin.getName(),
                admin.getPhoneNumber(),
                admin.getRole(),
                admin.getApprovedAt()
        );
    }
}
