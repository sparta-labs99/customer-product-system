package com.example.customerproductsystem.review.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/view/reviews")
public class ReviewViewController {

    @GetMapping("/list")
    public String reviewListPage() {
        return "review/list";
    }
}