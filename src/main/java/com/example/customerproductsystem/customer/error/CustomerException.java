package com.example.customerproductsystem.customer.error;

import com.example.customerproductsystem.common.error.CustomException;
import org.springframework.http.HttpStatus;

public class CustomerException {

    public static class NotFound extends CustomException {
        public NotFound(Long customerId) {
            super(HttpStatus.NOT_FOUND, "존재하지 않는 고객입니다. (ID: " + customerId + ")");
        }
    }

    public static class DuplicateEmail extends CustomException {
        public DuplicateEmail(String email) {
            super(HttpStatus.CONFLICT, "이미 존재하는 이메일입니다. (Email: " + email + ")");
        }
    }

    public static class AlreadyDeleted extends CustomException {
        public AlreadyDeleted(Long customerId) {
            super(HttpStatus.BAD_REQUEST, "이미 비활성화(탈퇴)된 고객입니다. (ID: " + customerId + ")");
        }
    }
}