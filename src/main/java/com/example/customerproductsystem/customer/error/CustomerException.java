package com.example.customerproductsystem.customer.error;

import com.example.customerproductsystem.common.error.CustomException;
import com.example.customerproductsystem.common.error.ErrorCode;

public class CustomerException {

    public static class NotFound extends CustomException {
        public NotFound(Long customerId) {
            super(ErrorCode.CUSTOMER_NOT_FOUND, "존재하지 않는 고객입니다. (ID: " + customerId + ")");
        }
    }

    public static class DuplicateEmail extends CustomException {
        public DuplicateEmail(String email) {
            super(ErrorCode.EMAIL_DUPLICATION, "이미 존재하는 이메일입니다. (Email: " + email + ")");
        }
    }

    public static class AlreadyDeleted extends CustomException {
        public AlreadyDeleted(Long customerId) {
            super(ErrorCode.ALREADY_INACTIVE_CUSTOMER, "이미 비활성화(탈퇴)된 고객입니다. (ID: " + customerId + ")");
        }
    }
}