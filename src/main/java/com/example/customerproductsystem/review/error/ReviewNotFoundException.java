package com.example.customerproductsystem.review.error;

import com.example.customerproductsystem.common.Error.CustomException;
import org.springframework.http.HttpStatus;

public class ReviewNotFoundException extends CustomException {

    public ReviewNotFoundException(String message) {

        super(HttpStatus.BAD_REQUEST, message);
    }

    public ReviewNotFoundException() {

        super(HttpStatus.BAD_REQUEST, "리뷰를 찾을 수 없습니다.");
    }
}
