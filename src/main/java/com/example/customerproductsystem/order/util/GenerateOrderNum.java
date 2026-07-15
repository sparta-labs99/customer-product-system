package com.example.customerproductsystem.order.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;

public class GenerateOrderNum {

    // 년도+월+일 - 4자리 랜덤숫자로 번호 생성 (예: ORD-20260713-0001)
    public static String generateOrderNumber() {
        // 1. yyyyMMdd 형식으로 날짜만 추출
        String date = LocalDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyyyMMdd"));

        // 2. 0 ~ 9999 사이의 난수 생성
        int random = ThreadLocalRandom.current().nextInt(10000);

        // 3. 4자리가 안 될 경우 앞에 0을 채워줌 (예: 1 -> 0001, 45 -> 0045)
        String formattedRandom = String.format("%04d", random);

        return "ORD-" + date + "-" + formattedRandom;
    }

}