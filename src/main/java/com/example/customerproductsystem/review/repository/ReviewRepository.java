package com.example.customerproductsystem.review.repository;


import com.example.customerproductsystem.review.entity.Review;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

@NullMarked
public interface ReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {

    // N+1 이슈 방지
    @EntityGraph(attributePaths = {"order","customer","product"})
    Page<Review> findAll(Specification<Review> reviewSpecification, Pageable pageable);

}
