package com.example.customerproductsystem.common.error;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // 공통 에러
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST, "C001", "잘못된 입력값입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "C002", "서버 에러가 발생했습니다."),
    UNAUTHORIZED(HttpStatus.UNAUTHORIZED, "C003", "인증이 필요합니다."),
    FORBIDDEN(HttpStatus.FORBIDDEN, "C004", "권한이 없습니다."),
    
    // Customer 도메인 에러
    CUSTOMER_NOT_FOUND(HttpStatus.NOT_FOUND, "CUS_001", "존재하지 않는 고객입니다."),
    EMAIL_DUPLICATION(HttpStatus.CONFLICT, "CUS_002", "이미 존재하는 이메일입니다."),
    ALREADY_INACTIVE_CUSTOMER(HttpStatus.BAD_REQUEST, "CUS_003", "이미 탈퇴 처리된 고객입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
