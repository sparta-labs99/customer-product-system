package com.example.customerproductsystem.customer.dto;

import com.example.customerproductsystem.customer.entity.CustomerStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerSearchCondition {
    private String keyword;                 // 검색 키워드 (이름 또는 이메일에 포함된 단어)
    private CustomerStatus status;          // 상태 필터: ACTIVE, INACTIVE, SUSPENDED (보내지 않으면 전체)
    private String sortBy = "createdAt";    // 정렬 기준: name, email, createdAt (기본값: 가입일)
    private String direction = "desc";      // 정렬 순서: asc, desc (기본값: 최신순)
}