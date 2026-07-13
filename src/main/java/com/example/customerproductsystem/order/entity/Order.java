package com.example.customerproductsystem.order.entity;

import com.example.customerproductsystem.common.entity.BaseEntity;
import com.example.customerproductsystem.order.tempFile.Admin;
import com.example.customerproductsystem.order.tempFile.Customer;
import com.example.customerproductsystem.product.entity.Product;
import jakarta.persistence.*;
import lombok.AccessLevel;
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

    // 관리자
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "admin_id")
    private Admin admin;

    // 주문 수량
    @Column(nullable = false)
    private Integer quantity;

    // 총 금액
    @Column(name = "total_price", nullable = false)
    private Long totalPrice;

    // 주문 상태
    // Enum 코드는 entity/OrderStatus에 있음
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus status;

    // 취소 사유
    @Column(name = "cancel_reason", length = 255)
    private String cancelReason;

    public Order(
            String orderNumber,
            Customer customer,
            Product product,
            Admin admin,
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
        this.status = status;
    }

    public void cancelOrder(String cancelReason){
        this.cancelReason = cancelReason;
        this.status = OrderStatus.CANCELED;
    }
    public void updateStatus(OrderStatus status) {
        this.status = status;
    }
}
