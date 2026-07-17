package com.example.customerproductsystem.common.error;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/error")
public class ErrorViewController {

    @GetMapping("/401")
    public String unauthorizedError() {
        return "error/401";
    }

    @GetMapping("/403")
    public String forbiddenError() {
        return "error/403";
    }

    @GetMapping("/404")
    public String notFoundError() {
        return "error/404";
    }
}
