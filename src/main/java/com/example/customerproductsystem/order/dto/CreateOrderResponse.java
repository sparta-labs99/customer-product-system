package com.example.customerproductsystem.order.dto;

import com.example.customerproductsystem.order.entity.Order;
import com.example.customerproductsystem.order.entity.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CreateOrderResponse {

    private final Long id;
    private final String orderNumber;
    private final String customerName;
    private final String productName;
    private final Integer quantity;
    private final Long totalPrice;
    private final OrderStatus status;



    public static CreateOrderResponse from(Order order) {
        return new CreateOrderResponse(
                order.getId(),
                order.getOrderNumber(),
                order.getCustomer().getName(),
                order.getProduct().getName(),
                order.getQuantity(),
                order.getTotalPrice(),
                order.getStatus()
        );
    }
}