package com.example.customerproductsystem.review.error;

import com.example.customerproductsystem.common.error.CustomException;
import org.springframework.http.HttpStatus;

public class InvalidReviewStatusException extends CustomException {

    public InvalidReviewStatusException(String message) {

        super(HttpStatus.BAD_REQUEST, message);
    }

    public InvalidReviewStatusException() {

        super(HttpStatus.BAD_REQUEST, "유효하지 않은 리뷰 상태 입니다.");
    }
}
