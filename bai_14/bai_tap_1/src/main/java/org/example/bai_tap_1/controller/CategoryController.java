package org.example.bai_tap_1.controller;

import org.example.bai_tap_1.service.ICategoryService;
import org.springframework.stereotype.Controller;

@Controller
public class CategoryController {
    private final ICategoryService categoryService;
    public CategoryController(ICategoryService categoryService){
        this.categoryService = categoryService;
    }

}
