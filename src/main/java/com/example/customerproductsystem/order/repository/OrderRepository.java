package com.example.customerproductsystem.order.repository;

import com.example.customerproductsystem.order.entity.Order;
import com.example.customerproductsystem.order.entity.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends JpaRepository<Order, Long> {
    // 1. 특정 고객의 총 주문 건수 계산
    long countByCustomerId(Long customerId);

    // 2. 특정 고객의 총 구매 금액 합산
    @Query("SELECT COALESCE(SUM(o.totalPrice), 0) FROM Order o WHERE o.customer.id = :customerId")
    long sumTotalPriceByCustomerId(@Param("customerId") Long customerId);

    @Query(value = "SELECT o FROM Order o " +
            "JOIN FETCH o.customer c " +
            "JOIN FETCH o.product p " +
            "LEFT JOIN FETCH o.admin a " +
            "WHERE (:status IS NULL OR o.status = :status) " +
            "AND (:keyword IS NULL OR o.orderNumber LIKE %:keyword% OR c.name LIKE %:keyword%)",
           countQuery = "SELECT count(o) FROM Order o " +
                   "JOIN o.customer c " +
                   "WHERE (:status IS NULL OR o.status = :status) " +
                   "AND (:keyword IS NULL OR o.orderNumber LIKE %:keyword% OR c.name LIKE %:keyword%)")
    Page<Order> findAllByCondition(
            @Param("status") OrderStatus status, 
            @Param("keyword") String keyword, 
            Pageable pageable);

    @Query("SELECT o FROM Order o " +
            "JOIN FETCH o.customer " +
            "JOIN FETCH o.product " +
            "LEFT JOIN FETCH o.admin " +
            "WHERE o.id = :id")
    java.util.Optional<Order> findByIdWithDetails(@Param("id") Long id);
}
