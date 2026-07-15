package com.example.customerproductsystem.order.dto;

import com.example.customerproductsystem.order.entity.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateOrderStatusRequest {
    
    @NotNull(message = "변경할 상태값을 입력해 주세요.")
    private OrderStatus status;
}
