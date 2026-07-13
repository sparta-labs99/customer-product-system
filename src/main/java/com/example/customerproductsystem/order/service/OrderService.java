package com.example.customerproductsystem.order.service;

import com.example.customerproductsystem.common.error.CustomException;
import com.example.customerproductsystem.order.dto.CreateOrderRequest;
import com.example.customerproductsystem.order.dto.CreateOrderResponse;
import com.example.customerproductsystem.order.entity.Order;
import com.example.customerproductsystem.order.entity.OrderStatus;
import com.example.customerproductsystem.order.repository.OrderRepository;
import com.example.customerproductsystem.order.tempFile.*;
import com.example.customerproductsystem.product.entity.Product;
import com.example.customerproductsystem.product.repository.ProductRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.customerproductsystem.order.util.GenerateOrderNum.generateOrderNumber;
import static com.example.customerproductsystem.order.validation.OrderValidation.validate;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final AdminRepository adminRepository;

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest request, HttpSession session) {

        //예외처리
        Customer customer = customerRepository.findById(request.getCustomerId()).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "검색한 고객 ID가 존재하지 않습니다.")
        );
        Product product = productRepository.findById(request.getProductId()).orElseThrow(
                () -> new CustomException(HttpStatus.NOT_FOUND, "입력하신 상품이 존재하지 않습니다.")
        );


        //관리자 권한 있다고 가정하고 임시 코드 생성 수정필수!!!!!!!!!
        //나중에 세션처리해야됨(getAttribute 사용하기)
        Admin admin = adminRepository.findById(1L)
                .orElse(null);


        //요청이 유효한지 검증 (남은 상품 수량이 없거나, 품절이거나, 단종됐거나, 현재 수량보다 많이 주문할경우에 대한 예외처리)
        validate(product, request.getQuantity());
        //상품 수량 업데이트 (주문한 수량만큼 상품수량에서 차감)
        product.updateStock(product.getStock() - request.getQuantity());

        // 주문번호 생성
        String orderNumber = generateOrderNumber();
        // 총 금액 계산
        Long totalPrice = (long)product.getPrice() * request.getQuantity();
        // 주문 생성
        Order order = new Order(
                orderNumber,
                customer,
                product,
                admin,
                request.getQuantity(),
                totalPrice,
                OrderStatus.PENDING
        );

        orderRepository.save(order);

        return new CreateOrderResponse(
                order.getId(),
                order.getOrderNumber(),
                customer.getName(),
                product.getName(),
                order.getQuantity(),
                order.getTotalPrice(),
                order.getStatus()
        );
    }
}
