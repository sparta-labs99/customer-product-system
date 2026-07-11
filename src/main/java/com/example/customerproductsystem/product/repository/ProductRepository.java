package com.example.customerproductsystem.product.repository;


import com.example.customerproductsystem.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {



}
