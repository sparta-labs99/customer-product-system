package com.example.customerproductsystem.order.validation;

import com.example.customerproductsystem.common.Error.CustomException;
import com.example.customerproductsystem.product.entity.Product;
import com.example.customerproductsystem.product.entity.ProductStatus;
import org.springframework.http.HttpStatus;

public class OrderValidation {

    //주문 생성시의 문제에 대해 오류 처리하는 코드입니다.
    public static void validate(Product product, int quantity) {

        if (quantity < 1) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "수량은 1 이상이어야 합니다.");
        }

        if (product.getStatus() == ProductStatus.DISCONTINUED) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "단종 상품입니다.");
        }

        if (product.getStatus() == ProductStatus.OUT_OF_STOCK) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "품절 상품입니다.");
        }

        if (product.getStock() < quantity) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "재고가 부족합니다.");
        }
    }
}