package com.example.customerproductsystem.product.repository;


import com.example.customerproductsystem.product.dto.ProductCategoryCount;
import com.example.customerproductsystem.product.entity.Product;
import com.example.customerproductsystem.product.entity.ProductStatus;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

@NullMarked
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    // N+1 이슈 방지
    // 모든 상품 찾기 (+ 조건)
    @EntityGraph(attributePaths = "admin")
    Page<Product> findAll(Specification<Product> productSpecification, Pageable pageable);

    // 특정 상태의 상품 개수
    long countByStatus(ProductStatus status);

    // 특정 상태가 아닌 상품 개수
    long countByStatusNot(ProductStatus status);

    // 재고가 일정 숫자 이하이고 특정 상태인 상품 개수
    long countByStockLessThanEqualAndStatus(int stock, ProductStatus status);

    // 상품 카테고리 분포
    @Query("""
        SELECT new com.example.customerproductsystem.product.dto.ProductCategoryCount(p.category, COUNT(p))
        FROM Product p
        GROUP BY p.category
    """)
    List<ProductCategoryCount> getCategoryDistribution();
}
