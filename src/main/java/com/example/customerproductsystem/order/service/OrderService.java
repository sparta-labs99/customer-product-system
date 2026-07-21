package com.example.customerproductsystem.order.service;

import com.example.customerproductsystem.admin.entity.Admin;
import com.example.customerproductsystem.admin.repository.AdminRepository;
import com.example.customerproductsystem.auth.LoginAdmin;
import com.example.customerproductsystem.common.error.CustomException;
import com.example.customerproductsystem.common.error.ErrorCode;
import com.example.customerproductsystem.customer.entity.Customer;
import com.example.customerproductsystem.customer.error.CustomerException;
import com.example.customerproductsystem.customer.repository.CustomerRepository;
import com.example.customerproductsystem.order.dto.CreateOrderRequest;
import com.example.customerproductsystem.order.dto.CreateOrderResponse;
import com.example.customerproductsystem.order.dto.OrderDetailResponse;
import com.example.customerproductsystem.order.dto.OrderSearchResponse;
import com.example.customerproductsystem.order.entity.Order;
import com.example.customerproductsystem.order.error.OrderException;
import com.example.customerproductsystem.order.repository.OrderRepository;
import com.example.customerproductsystem.product.entity.Product;
import com.example.customerproductsystem.order.dto.UpdateOrderResponse;
import com.example.customerproductsystem.order.dto.UpdateOrderStatusRequest;
import com.example.customerproductsystem.product.repository.ProductRepository;
import com.example.customerproductsystem.order.dto.CancelOrderResponse;
import com.example.customerproductsystem.order.dto.CancelOrderRequest;
import com.example.customerproductsystem.order.dto.OrderSearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import static com.example.customerproductsystem.order.util.GenerateOrderNum.generateOrderNumber;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final AdminRepository adminRepository;


    @Transactional
    public CreateOrderResponse createOrder(CreateOrderRequest request, LoginAdmin sessionAdmin) {

        if (sessionAdmin == null || sessionAdmin.id() == null) {
            throw new CustomException(HttpStatus.UNAUTHORIZED, "로그인한 관리자만 주문을 생성할 수 있습니다.");
        }

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new CustomerException.NotFound(request.getCustomerId()));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new CustomException(ErrorCode.PRODUCT_NOT_FOUND));

        Admin admin = adminRepository.getReferenceById(sessionAdmin.id());

        // 주문한 수량만큼 상품수량에서 차감
        product.decreaseStockForOrder(request.getQuantity());

        // 주문번호 생성
        String orderNumber = generateOrderNumber();

        // 주문 생성
        /* order, product 도메인 검증
         * 주문 수량이 1 이상인가?
         * 해당 상품이 단종(DISCONTINUED) 상태인가?
         * 해당 상품이 품절(OUT_OF_STOCK) 상태이거나 재고가 0인가?
         * 주문하려는 수량이 현재 남은 재고(stock)보다 큰가?
         * */
        Order order = request.toEntity(orderNumber, customer, product, admin);

        Order savedOrder = orderRepository.save(order);

        return CreateOrderResponse.from(savedOrder);
    }

    /**
     * CS 주문 리스트 조회 (페이징 및 검색)
     */
    @Transactional(readOnly = true)
    public Page<OrderSearchResponse> getAllOrders(
            OrderSearchCondition condition,
            Pageable pageable) {

        Page<Order> orders = orderRepository.findAllByCondition(
                condition.getStatus(),
                condition.getKeyword(),
                pageable
        );

        return orders.map(OrderSearchResponse::from);
    }

    /**
     * 주문 상세 조회
     */
    @Transactional(readOnly = true)
    public OrderDetailResponse getOrderDetail(Long id) {
        Order order = orderRepository.findByIdWithDetails(id)
                .orElseThrow(() -> new OrderException.NotFound(id));

        return OrderDetailResponse.from(order);
    }

    /**
     * 주문 상태 수정
     */
    @Transactional
    public UpdateOrderResponse updateOrderStatus(Long id, UpdateOrderStatusRequest request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderException.NotFound(id));

        order.changeStatus(request.getStatus());

        return UpdateOrderResponse.from(order);
    }

    /**
     * 주문 취소
     */
    @Transactional
    public CancelOrderResponse cancelOrder(Long id, CancelOrderRequest request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new OrderException.NotFound(id));

        order.cancelOrder(request.getCancelReason());

        return CancelOrderResponse.from(order);
    }
}
