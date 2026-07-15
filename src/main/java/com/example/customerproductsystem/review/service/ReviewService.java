package com.example.customerproductsystem.review.service;

import com.example.customerproductsystem.customer.entity.Customer;
import com.example.customerproductsystem.product.entity.Product;
import com.example.customerproductsystem.product.entity.ProductStatus;
import com.example.customerproductsystem.review.dto.GetReviewResponse;
import com.example.customerproductsystem.review.entity.Review;
import com.example.customerproductsystem.review.entity.ReviewStatus;
import com.example.customerproductsystem.review.error.ReviewNotFoundException;
import com.example.customerproductsystem.review.repository.ReviewRepository;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    @Transactional(readOnly = true)
    public GetReviewResponse getOne(Long id) {

        Review review = reviewRepository.findById(id).orElseThrow(
                ReviewNotFoundException::new);

        return GetReviewResponse.from(review);
    }

    @Transactional(readOnly = true)
    public Page<GetReviewResponse> getAll(String keyword, Integer rating, String status, Pageable pageable){

        ReviewStatus reviewStatus =
                (status == null || status.isEmpty())? null : ReviewStatus.from(status);

        Specification<Review> reviewSpecification = withCondition(keyword, reviewStatus, rating);

        Page<Review> reviews = reviewRepository.findAll(reviewSpecification, pageable);

        return reviews.map(GetReviewResponse::from);
    }

    public Specification<Review> withCondition(
            String keyword,
            ReviewStatus status,
            Integer rating
    ) {

        return (root, query, cb) -> {

            query.distinct(true);

            List<Predicate> predicates = new ArrayList<>();

            if (keyword != null && !keyword.isBlank()) {

                Join<Review, Customer> customerJoin = root.join("customer", JoinType.LEFT);
                Join<Review, Product> productJoin = root.join("product", JoinType.LEFT);

                Predicate customerNameLike = cb.like(customerJoin.get("name"), "%" + keyword + "%");
                Predicate productNameLike = cb.like(productJoin.get("name"), "%" + keyword + "%");

                predicates.add(cb.or(customerNameLike, productNameLike));
            }

            if (status != null) {
                predicates.add(
                        cb.equal(root.get("status"), status)
                );
            }

            // status가 DELETED가 아닌 경우, DELETED 제외
            if (status != ReviewStatus.DELETED) {
                predicates.add(
                        cb.notEqual(root.get("status"), ReviewStatus.DELETED)
                );
            }

            if (rating != null && rating > 0 && rating <= 5) {
                predicates.add(cb.equal(root.get("rating"), rating));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Transactional
    public void delete(Long id) {

        Review review = reviewRepository.findById(id).orElseThrow(
                ReviewNotFoundException::new);

        review.updateStatus(ReviewStatus.DELETED);

        reviewRepository.save(review);
    }

    @Transactional
    public void deleteAll(List<Long> ids) {

        List<Review> reviews = reviewRepository.findAllById(ids);

        if (reviews.size() != ids.size()) {
            // 에러 추후 수정
            throw new IllegalArgumentException("존재하지 않는 상품이 포함되어 있습니다.");
        }

        reviews.forEach(review -> review.updateStatus(ReviewStatus.DELETED));
    }
}
