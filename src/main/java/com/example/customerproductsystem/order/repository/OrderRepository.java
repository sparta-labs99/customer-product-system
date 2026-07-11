package com.example.customerproductsystem.order.repository;

import com.example.customerproductsystem.order.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
