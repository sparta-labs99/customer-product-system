package com.example.customerproductsystem.admin.dto;
import com.example.customerproductsystem.admin.entity.AdminRole;
import jakarta.validation.constraints.NotNull;

public record AdminRoleChangeRequest(
        @NotNull(message = "변경할 관리자 역할은 필수입니다.")
        AdminRole role
) {
}