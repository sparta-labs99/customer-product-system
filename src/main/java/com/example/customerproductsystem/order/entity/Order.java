package com.example.customerproductsystem.order.entity;

import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.common.entity.BaseEntity;
import com.example.customerproductsystem.customer.entity.Customer;
import com.example.customerproductsystem.product.entity.Product;
import com.example.customerproductsystem.order.error.OrderException;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "orders")
public class Order extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 주문번호
    @Column(name = "order_number", nullable = false, unique = true, length = 50)
    private String orderNumber;

    // 주문 고객
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    // 주문 상품
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // 관리자 (CS 대리 주문일 때만 저장->null 허용)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id", nullable = true)
    private Admin admin;

    // 주문 수량
    @Column(nullable = false)
    private Integer quantity;

    // 총 금액
    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

    // 주문 상태
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;

    // 취소 사유
    @Column(name = "cancel_reason", length = 255)
    private String cancelReason;

    @Builder
    public Order(
            String orderNumber,
            Customer customer,
            Product product,
            Admin admin, // 일반 주문 시 null 가능
            Integer quantity,
            Long totalPrice,
            OrderStatus status
    ) {
        this.orderNumber = orderNumber;
        this.customer = customer;
        this.product = product;
        this.admin = admin;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status != null ? status : OrderStatus.PENDING;
    }

    public void cancelOrder(String cancelReason) {
        if (this.status != OrderStatus.PENDING) {
            throw new OrderException.CannotCancelOrder(this.status.name());
        }

        this.product.restoreStockForCancel(this.quantity);
        this.cancelReason = cancelReason;
        this.status = OrderStatus.CANCELED;
    }

    public void changeStatus(OrderStatus newStatus) {
        if (this.status == OrderStatus.CANCELED) {
            throw new OrderException.InvalidStatusTransition(this.status.name(), newStatus.name());
        }

        if (this.status == OrderStatus.COMPLETED) {
            throw new OrderException.InvalidStatusTransition(this.status.name(), newStatus.name());
        }

        if (this.status == OrderStatus.PENDING && newStatus != OrderStatus.SHIPPING) {
            throw new OrderException.InvalidStatusTransition(this.status.name(), newStatus.name());
        }

        if (this.status == OrderStatus.SHIPPING && newStatus != OrderStatus.COMPLETED) {
            throw new OrderException.InvalidStatusTransition(this.status.name(), newStatus.name());
        }

        this.status = newStatus;
    }
}