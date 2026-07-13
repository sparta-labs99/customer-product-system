package com.example.customerproductsystem.admin.dto;

import com.example.customerproductsystem.admin.entity.Admin;

public record MyProfileResponse(
        Long id,
        String name,
        String email,
        String phoneNumber
) {
    public static MyProfileResponse from(Admin admin) {
        return new MyProfileResponse(
                admin.getId(),
                admin.getName(),
                admin.getEmail(),
                admin.getPhoneNumber()
        );
    }
}
