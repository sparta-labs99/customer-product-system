package com.example.customerproductsystem.admin.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AdminRejectRequest(
        @NotBlank(message = "거부 사유는 필수입니다.")
        @Size(max = 500, message = "거부 사유는 500자 이하여야 합니다.")
        String reason
) {
}
