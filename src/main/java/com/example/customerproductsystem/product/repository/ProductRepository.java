package com.example.customerproductsystem.product.repository;


import com.example.customerproductsystem.product.entity.Product;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@NullMarked
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

    // N+1 이슈 방지
    @EntityGraph(attributePaths = "admin")
    Page<Product> findAll(Specification<Product> productSpecification, Pageable pageable);


}
