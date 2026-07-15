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

    public Product(String name, Categories category, int price, int stock, ProductStatus status, Admin admin) {
        this.name = name;
        this.category = category;
        this.price = price;
        this.stock = stock;
        this.status = status;
        this.admin = admin;
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

        // 단종 상태인 경우 재고만 변경하고 상태 자동 전환은 무시
        if (this.status == ProductStatus.DISCONTINUED) {
            return;
        }

        if (stock == 0) {
            this.status = ProductStatus.OUT_OF_STOCK;
        }
        else {
            this.status = ProductStatus.FOR_SALE;
        }
    }

    public void updateStatus(ProductStatus status) {
        this.status = status;
    }

    /*주문 재고 차감*/
    public void decreaseStockForOrder(int orderQuantity) {
        if (this.status == ProductStatus.DISCONTINUED) {
            throw new IllegalArgumentException("단종된 상품으로 주문할 수 없습니다.");
        }

        if (this.status == ProductStatus.OUT_OF_STOCK || this.stock == 0) {
            throw new IllegalArgumentException("품절된 상품으로 주문할 수 없습니다.");
        }

        if (this.stock < orderQuantity) {
            throw new IllegalArgumentException("상품의 재고가 부족합니다. (현재 재고: " + this.stock + ")");
        }

        int remainingStock = this.stock - orderQuantity;
        this.updateStock(remainingStock);
    }

    /*주문 취소로 인한 재고 복구*/
    public void restoreStockForCancel(int orderQuantity) {
        this.updateStock(this.stock + orderQuantity);
    }
}

