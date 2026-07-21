package com.example.customerproductsystem.order.entity;

public enum OrderStatus {

    // OrderStatus라는 클래스로 만들어진 '객체(인스턴스)'
    // public static final OrderStatus PENDING = new OrderStatus() {
    PENDING {
        @Override
        public boolean canChangeTo(OrderStatus newStatus) {
            // "나(PENDING)는 새 상태(newStatus)가 SHIPPING이거나 CANCELED일 때만 true야!"
            return newStatus == SHIPPING || newStatus == CANCELED;
        }
    },
    SHIPPING {
        @Override
        public boolean canChangeTo(OrderStatus newStatus) {
            // "나(SHIPPING)는 오직 COMPLETED(완료)로만 갈 수 있어!"
            return newStatus == COMPLETED;
        }
    },
    COMPLETED {
        @Override
        public boolean canChangeTo(OrderStatus newStatus) {
            return false;
        }
    },
    CANCELED {
        @Override
        public boolean canChangeTo(OrderStatus newStatus) {
            return false;
        }
    };

    // 각 상태별로 오버라이딩된 로직을 실행하는 추상 메서드
    public abstract boolean canChangeTo(OrderStatus newStatus);
}