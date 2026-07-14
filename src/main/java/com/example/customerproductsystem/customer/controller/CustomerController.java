package com.example.customerproductsystem.customer.controller;

import com.example.customerproductsystem.customer.dto.*;
import com.example.customerproductsystem.customer.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customers")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    /**
     * 고객 생성
     */
    @PostMapping
    public ResponseEntity<CreateCustomerResponse> createCustomer(@Valid @RequestBody CreateCustomerRequest request) {
        CreateCustomerResponse response = customerService.createCustomer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * 고객 조회 (페이징)
     */
    @GetMapping
    public ResponseEntity<Page<CreateCustomerResponse>> getAllCustomers(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            CustomerSearchCondition condition) {

        // 1. 정렬 순서  (asc / desc)
        Sort.Direction dir = condition.getDirection().equalsIgnoreCase("asc") ?
                Sort.Direction.ASC : Sort.Direction.DESC;

        // 2. 정렬 기준 필드
        String sortProperty = condition.getSortBy();
        if (!sortProperty.equals("name") && !sortProperty.equals("email") && !sortProperty.equals("createdAt")) {
            sortProperty = "createdAt";
        }

        // 3. 페이징 객체 조립 (page - 1 보정)
        int pageIndex = Math.max(0, page - 1);
        Pageable pageable = PageRequest.of(pageIndex, size, Sort.by(dir, sortProperty));

        Page<CreateCustomerResponse> responses = customerService.getAllCustomers(condition, pageable);
        return ResponseEntity.ok(responses);
    }

    /**
     * 고객 상세 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<CustomerDetailResponse> getCustomerDetail(@PathVariable Long id) {
        CustomerDetailResponse response = customerService.getCustomerDetail(id);
        return ResponseEntity.ok(response);
    }

    /**
     * 고객 정보 수정
     */
    @PutMapping("/{id}")
    public ResponseEntity<UpdateCustomerResponse> updateCustomer(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCustomerRequest request) {
        UpdateCustomerResponse response = customerService.updateCustomer(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * 고객 상태 수정
     */
    @PatchMapping("/{id}")
    public ResponseEntity<UpdateCustomerResponse> updateCustomerStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateCustomerStatusRequest request) {
        UpdateCustomerResponse response = customerService.updateCustomerStatus(id, request);
        return ResponseEntity.ok(response);
    }

    /**
     * 고객 다중 삭제 (선택 삭제)
     */
    @DeleteMapping
    public ResponseEntity<Void> deleteCustomers(@RequestParam List<Long> ids) {
        customerService.deleteCustomers(ids);
        return ResponseEntity.noContent().build();
    }

    /**
     * 고객 삭제 (상태 변경 - Soft Delete)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) {
        customerService.deleteCustomer(id);
        return ResponseEntity.noContent().build();
    }
}