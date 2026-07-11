package com.example.customerproductsystem.order.dto;

import com.example.customerproductsystem.order.entity.OrderStatus;
import lombok.Getter;

@Getter
public class CreateOrderResponse {

    private final Long id;
    private final String orderNumber;
    private final String customerName;
    private final String productName;
    private final Integer quantity;
    private final Long totalPrice;
    private final OrderStatus status;

    public CreateOrderResponse(Long id, String orderNumber, String customerName, String productName, Integer quantity, Long totalPrice, OrderStatus status) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.customerName = customerName;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
    }

}
