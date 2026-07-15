package com.example.customerproductsystem.order.error;

import com.example.customerproductsystem.common.error.CustomException;
import com.example.customerproductsystem.common.error.ErrorCode;

public class OrderException {

    public static class NotFound extends CustomException {
        public NotFound(Long orderId) {
            super(ErrorCode.ORDER_NOT_FOUND, "존재하지 않는 주문입니다. (ID: " + orderId + ")");
        }
    }

    public static class InvalidStatusTransition extends CustomException {
        public InvalidStatusTransition(String currentStatus, String nextStatus) {
            super(ErrorCode.INVALID_ORDER_STATUS_TRANSITION, 
                  String.format("'%s' 상태에서 '%s' 상태로 변경할 수 없습니다.", currentStatus, nextStatus));
        }
    }

    public static class CannotCancelOrder extends CustomException {
        public CannotCancelOrder(String currentStatus) {
            super(ErrorCode.CANNOT_CANCEL_ORDER, "준비중(PENDING) 상태의 주문만 취소할 수 있습니다. 현재 상태: " + currentStatus);
        }
    }
}
