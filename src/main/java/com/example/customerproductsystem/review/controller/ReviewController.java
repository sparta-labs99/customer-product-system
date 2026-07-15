package com.example.customerproductsystem.review.controller;

import com.example.customerproductsystem.review.dto.GetReviewResponse;
import com.example.customerproductsystem.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("reviews/{id}")
    public ResponseEntity<GetReviewResponse> getReview(
            @PathVariable Long id
    ) {

        GetReviewResponse result = reviewService.getOne(id);

        return ResponseEntity.ok().body(result);
    }

    // 리뷰 전체 보기
    @GetMapping("reviews")
    public ResponseEntity<Page<GetReviewResponse>> getAllReviews(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) Integer rating,
            @RequestParam(required = false) String status,
            Pageable pageable // / url에서 자동으로 page, size, sort 값을 저장
    ) {

        Page<GetReviewResponse> result = reviewService.getAll(keyword, rating, status, pageable);

        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("reviews/{id}")
    public ResponseEntity<Void> deleteReview(
            @PathVariable Long id
    ) {

        reviewService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("reviews")
    public ResponseEntity<Void> deleteReview(
            @RequestBody List<Long> ids
    ) {

        reviewService.deleteAll(ids);

        return ResponseEntity.noContent().build();
    }
}
