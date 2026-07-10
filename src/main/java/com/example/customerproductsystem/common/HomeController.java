package com.example.customerproductsystem.common;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    /**
     * 웹 사이트의 메인 주소(http://localhost:8080/)로 접근했을 때
     * templates/index.html 대시보드 화면을 띄워줍니다.
     */
    @GetMapping("/")
    public String home() {
        return "customer/list";
    }
}