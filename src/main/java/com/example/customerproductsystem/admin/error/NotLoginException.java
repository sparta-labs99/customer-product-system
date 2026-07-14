package com.example.customerproductsystem.admin.error;

import com.example.customerproductsystem.common.error.CustomException;
import org.springframework.http.HttpStatus;

public class NotLoginException extends CustomException {

    public NotLoginException(String message) {

        super(HttpStatus.UNAUTHORIZED, message);
    }

    public NotLoginException() {

        super(HttpStatus.UNAUTHORIZED, "로그인이 필요한 기능입니다.");
    }
}
