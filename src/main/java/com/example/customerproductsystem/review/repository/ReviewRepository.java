package com.example.customerproductsystem.review.repository;


import com.example.customerproductsystem.review.dto.RatingCountDto;
import com.example.customerproductsystem.review.entity.Review;
import org.jspecify.annotations.NullMarked;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@NullMarked
public interface ReviewRepository extends JpaRepository<Review, Long>, JpaSpecificationExecutor<Review> {

    // N+1 이슈 방지
    @EntityGraph(attributePaths = {"order","customer","product"})
    Page<Review> findAll(Specification<Review> reviewSpecification, Pageable pageable);

    @EntityGraph(attributePaths = "product")
    List<Review> findTop3ByProductIdOrderByCreatedAtDesc(Long productId);

    @Query("""
        SELECT new com.example.customerproductsystem.review.dto.RatingCountDto(r.rating, COUNT(r))
        FROM Review r
        WHERE r.product.id = :productId AND r.rating <= 5
        GROUP BY r.rating
        ORDER BY r.rating DESC
    """)
    List<RatingCountDto> countByRating(@Param("productId") Long productId);
}
