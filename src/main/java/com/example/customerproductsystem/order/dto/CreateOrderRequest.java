package com.example.customerproductsystem.order.dto;

import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.customer.entity.Customer;
import com.example.customerproductsystem.order.entity.Order;
import com.example.customerproductsystem.order.entity.OrderStatus;
import com.example.customerproductsystem.product.entity.Product;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CreateOrderRequest {

    @NotNull(message = "고객 ID는 필수입니다.")
    private Long customerId;

    @NotNull(message = "상품 ID는 필수입니다.")
    private Long productId;

    @NotNull(message = "수량은 필수입니다.")
    @Min(value = 1, message = "수량은 1 이상이어야 합니다.")
    private Integer quantity;

    public Order toEntity(String orderNumber, Customer customer, Product product, Admin admin) {
        // 주문 당시 상품 가격 기준으로 총 금액 계산
        long totalPrice = (long) product.getPrice() * this.quantity;

        return Order.builder()
                .orderNumber(orderNumber)
                .customer(customer)
                .product(product)
                .admin(admin)
                .quantity(this.quantity)
                .totalPrice(totalPrice)
                .status(OrderStatus.PENDING)
                .build();
    }
}