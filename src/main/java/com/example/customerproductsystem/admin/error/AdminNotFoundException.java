package com.example.customerproductsystem.admin.error;

import com.example.customerproductsystem.common.error.CustomException;
import org.springframework.http.HttpStatus;

public class AdminNotFoundException extends CustomException {

    public AdminNotFoundException(String message) {

        super(HttpStatus.BAD_REQUEST, message);
    }

    public AdminNotFoundException() {

        super(HttpStatus.BAD_REQUEST, "관리자를 찾을 수 없습니다.");
    }
}
