package com.example.customerproductsystem.customer.entity;

public enum CustomerStatus {

    ACTIVE {
        @Override
        public boolean canChangeTo(CustomerStatus newStatus) {
            return newStatus == SUSPENDED || newStatus == INACTIVE;
        }
    },
    INACTIVE {
        @Override
        public boolean canChangeTo(CustomerStatus newStatus) {
            return false; // 탈퇴(비활성) 상태에서는 상태 변경 불가
        }
    },
    SUSPENDED {
        @Override
        public boolean canChangeTo(CustomerStatus newStatus) {
            return newStatus == ACTIVE || newStatus == INACTIVE;
        }
    };

    public abstract boolean canChangeTo(CustomerStatus newStatus);
}