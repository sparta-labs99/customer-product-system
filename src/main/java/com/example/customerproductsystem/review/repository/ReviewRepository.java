package com.example.customerproductsystem.review.repository;


import com.example.customerproductsystem.review.dto.ReviewRatingCount;
import com.example.customerproductsystem.review.entity.Review;
import com.example.customerproductsystem.review.entity.ReviewStatus;
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
    // 모든 리뷰 찾기 (+조건)
    @EntityGraph(attributePaths = {"order","customer","product"})
    Page<Review> findAll(Specification<Review> reviewSpecification, Pageable pageable);

    // 가장 최근 리뷰 3개 찾기
    @EntityGraph(attributePaths = "product")
    List<Review> findTop3ByProductIdAndStatusNotOrderByCreatedAtDesc(Long productId, ReviewStatus status);

    // 상품에 대한 모든 리뷰 찾기
    @EntityGraph(attributePaths = "product")
    List<Review> findAllByProductId(Long productId);

    //상품에 대한 평점 분포 찾기
    @Query("""
        SELECT new com.example.customerproductsystem.review.dto.ReviewRatingCount(r.rating, COUNT(r))
        FROM Review r
        WHERE r.product.id = :productId AND r.rating <= 5 AND r.status != ReviewStatus.DELETED
        GROUP BY r.rating
        ORDER BY r.rating DESC
    """)
    List<ReviewRatingCount> getRatingDistributionByProductId(@Param("productId") Long productId);

    // 특정 상태가 아닌 리뷰 개수 찾기
    long countByStatusNot(ReviewStatus status);

    // 모든 평점 평균 찾기
    @Query("""
        SELECT COALESCE(AVG(r.rating),0)
        FROM Review r
        WHERE r.rating > 0 AND r.rating <= 5 AND r.status != ReviewStatus.DELETED
    """)
    double getRatingAverage();

    // 모든 평점 분포 찾기
    @Query("""
        SELECT new com.example.customerproductsystem.review.dto.ReviewRatingCount(r.rating, COUNT(r))
        FROM Review r
        WHERE r.rating <= 5 AND r.status != ReviewStatus.DELETED
        GROUP BY r.rating
        ORDER BY r.rating DESC
    """)
    List<ReviewRatingCount> getRatingDistribution();
}
