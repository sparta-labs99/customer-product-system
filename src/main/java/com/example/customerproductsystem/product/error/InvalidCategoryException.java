package com.example.customerproductsystem.product.error;

import com.example.customerproductsystem.common.Error.CustomException;
import org.springframework.http.HttpStatus;

public class InvalidCategoryException extends CustomException {

    public InvalidCategoryException() {

        super(HttpStatus.BAD_REQUEST, "유효하지 않은 상태 요청입니다.");
    }
}
