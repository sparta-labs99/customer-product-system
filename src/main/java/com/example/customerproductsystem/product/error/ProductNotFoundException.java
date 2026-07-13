package com.example.customerproductsystem.product.error;

import com.example.customerproductsystem.common.error.CustomException;
import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends CustomException {

    public ProductNotFoundException(String message) {

        super(HttpStatus.BAD_REQUEST, message);
    }

    public ProductNotFoundException() {

        super(HttpStatus.BAD_REQUEST, "상품을 찾을 수 없습니다.");
    }
}
