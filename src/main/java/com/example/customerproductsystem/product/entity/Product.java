package com.example.customerproductsystem.product.entity;


import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category", length = 50, nullable = false)
    private Categories category;

    @Column
    private int price;

    @Column
    private int stock;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 20, nullable = false)
    private ProductStatus status;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "admin_id", nullable = false)
    private Admin admin;

    public Product(String name, Categories category, int price, int stock, ProductStatus status) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.status = status;
    }

    public void update(String name, Categories category, int price) {
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public void updateStock(int stock) {
        this.stock = stock;

        if (stock < 0) {
            throw new IllegalArgumentException("재고는 0보다 작을 수 없습니다.");
        }

        if (this.status != ProductStatus.OUT_OF_STOCK && stock == 0) {
            this.status = ProductStatus.OUT_OF_STOCK;
        }
    }

    public void updateStatus(ProductStatus status) {
        this.status = status;
    }
}

