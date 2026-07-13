package com.example.customerproductsystem.customer.dto;

import com.example.customerproductsystem.customer.entity.CustomerStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateCustomerStatusRequest {

    @NotNull(message = "변경할 상태값은 필수 항목입니다.")
    private CustomerStatus status;

    @Builder
    public UpdateCustomerStatusRequest(CustomerStatus status) {
        this.status = status;
    }
}