package com.example.customerproductsystem.product.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/products")
public class ProductViewController {

    @GetMapping("/list")
    public String productListPage() {
        return "product/list";
    }

    @GetMapping("/{id}")
    public String productDetailPage(@PathVariable Long id, Model model) {
        model.addAttribute("productId", id);
        return "product/detail";
    }
}