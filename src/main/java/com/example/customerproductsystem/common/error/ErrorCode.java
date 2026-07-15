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
    ALREADY_INACTIVE_CUSTOMER(HttpStatus.BAD_REQUEST, "CUS_003", "이미 탈퇴 처리된 고객입니다."),

    // Admin 도메인 에러
    ADMIN_NOT_FOUND(HttpStatus.NOT_FOUND, "ADM_001", "관리자를 찾을 수 없습니다."),
    ADMIN_DUPLICATE_EMAIL(HttpStatus.CONFLICT, "ADM_002", "이미 존재하는 관리자 이메일입니다."),
    ADMIN_LOGIN_REQUIRED(HttpStatus.UNAUTHORIZED, "ADM_003", "로그인이 필요한 기능입니다."),
    ADMIN_LOGIN_FAILED(HttpStatus.UNAUTHORIZED, "ADM_004", "이메일 또는 비밀번호가 일치하지 않습니다."),
    ADMIN_PENDING(HttpStatus.FORBIDDEN, "ADM_005", "승인 대기 중인 관리자입니다."),
    ADMIN_REJECTED(HttpStatus.FORBIDDEN, "ADM_006", "가입 신청이 거부된 관리자입니다."),
    ADMIN_SUSPENDED(HttpStatus.FORBIDDEN, "ADM_007", "정지된 관리자입니다."),
    ADMIN_INACTIVE(HttpStatus.FORBIDDEN, "ADM_008", "비활성화된 관리자입니다."),
    SUPER_ADMIN_REQUIRED(HttpStatus.FORBIDDEN, "ADM_009", "슈퍼 관리자 권한이 필요합니다."),
    ADMIN_LAST_SUPER_ADMIN(HttpStatus.CONFLICT, "ADM_013", "마지막 활성 슈퍼 관리자는 보호됩니다."),
    ADMIN_CURRENT_PASSWORD_MISMATCH(HttpStatus.BAD_REQUEST, "ADM_014", "현재 비밀번호가 일치하지 않습니다."),
    ADMIN_SAME_PASSWORD(HttpStatus.BAD_REQUEST, "ADM_015", "새 비밀번호는 기존 비밀번호와 같을 수 없습니다."),
    ADMIN_SAME_ROLE(HttpStatus.BAD_REQUEST, "ADM_016", "현재 역할과 동일한 역할로 변경할 수 없습니다."),
    ADMIN_INVALID_SESSION(HttpStatus.UNAUTHORIZED, "ADM_017", "유효하지 않은 로그인 정보입니다."),
    ADMIN_SAME_STATUS(HttpStatus.BAD_REQUEST, "ADM_018", "현재 상태와 동일한 상태로 변경할 수 없습니다."),
    ADMIN_NOT_PENDING(HttpStatus.BAD_REQUEST, "ADM_019", "승인 대기 상태의 관리자만 승인 또는 거부할 수 있습니다."),
    ADMIN_INVALID_STATUS_CHANGE(HttpStatus.BAD_REQUEST, "ADM_020", "승인 대기와 거부 상태는 상태 변경 API로 설정할 수 없습니다."),
    ADMIN_LAST_ACTIVE_SUPER_ADMIN_ROLE_CHANGE(HttpStatus.BAD_REQUEST, "ADM_021", "마지막 활성 슈퍼 관리자의 역할은 변경할 수 없습니다."),
    ADMIN_INVALID_SORT(HttpStatus.BAD_REQUEST, "ADM_022", "지원하지 않는 정렬 기준입니다."),
    ADMIN_INVALID_SORT_DIRECTION(HttpStatus.BAD_REQUEST, "ADM_023", "정렬 방향은 asc 또는 desc만 사용할 수 있습니다."
    );
    // Product 도메인 에러
    PRODUCT_NOT_FOUND(HttpStatus.NOT_FOUND, "PRO_001", "존재하지 않는 상품입니다."),
    INVALID_CATEGORY(HttpStatus.BAD_REQUEST, "PRO_002", "유효하지 않은 카테고리 요청입니다."),
    INVALID_PRODUCT_STATUS(HttpStatus.BAD_REQUEST, "PRO_003", "유효하지 않은 상태 요청입니다."),

    // REVIEW 도메인 에러
    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND, "REV_001", "존재하지 않는 리뷰입니다."),
    INVALID_REVIEW_STATUS(HttpStatus.BAD_REQUEST, "REV_002", "유효하지 않은 상태 요청입니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
