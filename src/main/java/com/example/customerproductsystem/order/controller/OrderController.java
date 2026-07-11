package com.example.customerproductsystem.order.controller;

import com.example.customerproductsystem.order.dto.CreateOrderRequest;
import com.example.customerproductsystem.order.dto.CreateOrderResponse;
import com.example.customerproductsystem.order.service.OrderService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final OrderService orderService;

    //주문 생성 컨트롤러
    @PostMapping
    public ResponseEntity<CreateOrderResponse> createOrder(
            @Valid @RequestBody CreateOrderRequest request,
            HttpSession session
    )
    {
        CreateOrderResponse response = orderService.createOrder(request, session);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
