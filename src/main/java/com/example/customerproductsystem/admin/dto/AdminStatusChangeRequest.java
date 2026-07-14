package com.example.customerproductsystem.admin.dto;

import com.example.customerproductsystem.admin.entity.AdminStatus;
import jakarta.validation.constraints.NotNull;

public record AdminStatusChangeRequest(

        @NotNull(message = "변경할 관리자 상태는 필수입니다.")
        AdminStatus status
) {
}
