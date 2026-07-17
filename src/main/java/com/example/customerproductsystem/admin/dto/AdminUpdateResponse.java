package com.example.customerproductsystem.admin.dto;

import com.example.customerproductsystem.admin.entity.Admin;

public record AdminUpdateResponse(
        Long id,
        String name,
        String phoneNumber
) {

    public static AdminUpdateResponse from(Admin admin) {
        return new AdminUpdateResponse(
                admin.getId(),
                admin.getName(),
                admin.getPhoneNumber()
        );
    }
}
