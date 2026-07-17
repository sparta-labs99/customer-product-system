package com.example.customerproductsystem.order.controller;

import com.example.customerproductsystem.auth.LoginAdmin;
import com.example.customerproductsystem.auth.SessionConst;
import com.example.customerproductsystem.common.response.ApiResponse;
import com.example.customerproductsystem.order.dto.*;
import com.example.customerproductsystem.order.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    /**
     * CS 주문
     */
    @PostMapping
    public ResponseEntity<ApiResponse<CreateOrderResponse>> createOrder(
            @Valid @RequestBody CreateOrderRequest request,
            @SessionAttribute(name = SessionConst.LOGIN_ADMIN, required = false) LoginAdmin sessionAdmin
    )
    {
        CreateOrderResponse response = orderService.createOrder(request, sessionAdmin);

        return ResponseEntity.status(HttpStatus.CREATED).body(ApiResponse.success(response));
    }

    /**
     * 주문 리스트 조회 (페이징 및 검색)
     */
    @GetMapping
    public ResponseEntity<ApiResponse<Page<OrderSearchResponse>>> getAllOrders(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            OrderSearchCondition condition) {

        Sort.Direction dir = condition.getDirection().equalsIgnoreCase("asc") ?
                Sort.Direction.ASC : Sort.Direction.DESC;

        String sortProperty = condition.getSortBy();
        if (!sortProperty.equals("quantity") && !sortProperty.equals("totalPrice") && !sortProperty.equals("createdAt")) {
            sortProperty = "createdAt";
        }

        int pageIndex = Math.max(0, page - 1);
        Pageable pageable = PageRequest.of(pageIndex, size, Sort.by(dir, sortProperty));

        Page<OrderSearchResponse> response = orderService.getAllOrders(condition, pageable);

        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * 주문 상세 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderDetailResponse>> getOrderDetail(@PathVariable Long id) {
        OrderDetailResponse response = orderService.getOrderDetail(id);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * 주문 상태 수정
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<ApiResponse<UpdateOrderResponse>> updateOrderStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateOrderStatusRequest request) {
        UpdateOrderResponse response = orderService.updateOrderStatus(id, request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }

    /**
     * 주문 취소
     */
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<ApiResponse<CancelOrderResponse>> cancelOrder(
            @PathVariable Long id,
            @Valid @RequestBody CancelOrderRequest request) {
        CancelOrderResponse response = orderService.cancelOrder(id, request);
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
