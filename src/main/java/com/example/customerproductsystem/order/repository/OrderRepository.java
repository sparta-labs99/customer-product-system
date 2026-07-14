package com.example.customerproductsystem.order.repository;

import com.example.customerproductsystem.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // 1. 특정 고객의 총 주문 건수 계산
    long countByCustomerId(Long customerId);

    // 2. 특정 고객의 총 구매 금액 합산
    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM Order o WHERE o.customer.id = :customerId")
    long sumTotalPriceByCustomerId(@Param("customerId") Long customerId);
}
