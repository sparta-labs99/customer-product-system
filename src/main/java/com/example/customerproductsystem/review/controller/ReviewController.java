package com.example.customerproductsystem.review.controller;

import com.example.customerproductsystem.review.dto.GetReviewResponse;
import com.example.customerproductsystem.review.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("reviews/{id}")
    public ResponseEntity<GetReviewResponse> getOnd(
            @PathVariable Long id
    ) {
        GetReviewResponse result = reviewService.getOne(id);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("reviews")
    public ResponseEntity<List<GetReviewResponse>> getAll(
            @RequestParam String keyword,
            @RequestParam int rating,
            @RequestParam String status,
            Pageable pageable
    ) {
        List<GetReviewResponse> result = reviewService.getAll(keyword, rating, status, pageable);

        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("reviews/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        reviewService.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
