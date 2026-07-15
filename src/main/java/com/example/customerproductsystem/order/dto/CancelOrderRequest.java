package com.example.customerproductsystem.order.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CancelOrderRequest {
    
    @NotBlank(message = "취소 사유를 입력해 주세요.")
    private String cancelReason;
}
