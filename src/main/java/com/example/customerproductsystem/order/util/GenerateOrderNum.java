package com.example.customerproductsystem.order.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;//주문번호를 생성해주는 비즈니스로직

//년도+월+일+시간+분+초 - 랜덤한숫자로 번호 생성
public class GenerateOrderNum {

    public static String generateOrderNumber() {
        String time = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));

        int random = ThreadLocalRandom.current().nextInt(100, 1000);

        return "ORD-" + time + "-" + random;
    }

}
