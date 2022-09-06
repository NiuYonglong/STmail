package com.situ.stmail.front.controller.api;

import com.situ.stmail.front.entity.Result;
import com.situ.stmail.front.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category")
@AllArgsConstructor
public class APICategoryController {
    private CategoryService categoryService;
    @GetMapping("/getParent")
    public Result getParent(){

        return Result.success(categoryService.getParent());
    }
}
