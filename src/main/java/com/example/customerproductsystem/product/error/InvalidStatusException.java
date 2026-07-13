package com.example.customerproductsystem.product.error;

import com.example.customerproductsystem.common.Error.CustomException;
import org.springframework.http.HttpStatus;

public class InvalidStatusException extends CustomException {

    public InvalidStatusException(String message) {

        super(HttpStatus.BAD_REQUEST, message);
    }

    public InvalidStatusException() {

        super(HttpStatus.BAD_REQUEST, "유효하지 않은 카테고리 요청입니다.");
    }
}
