package com.situ.stmail.manager.controller.api;

import com.github.pagehelper.PageInfo;
import com.situ.stmail.manager.entity.Category;
import com.situ.stmail.manager.entity.Result;
import com.situ.stmail.manager.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController //保证返回json格式数据

@RequestMapping("/api/category") //增加URL前缀
//添加URL前缀
public class APICategoryController {
    @Resource
    private CategoryService categoryService;

    @PostMapping
    public Result add(@RequestBody Category category){
        try {
            categoryService.add(category);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
        return Result.success();
    }

    @DeleteMapping
    public Result remove(Integer id){
        try {
            int code = categoryService.remove(id);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
        return Result.success();
    }
    @PutMapping
    public Result edit(@RequestBody Category category){
        try {
            int code = categoryService.edit(category);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
        return Result.success();
    }
    @GetMapping
    public Result get(Integer page,Integer limit,Category category){
        if(page==null) {
            List<Category> list = categoryService.search(category);
            return Result.success(list);
        }else {
            PageInfo pageInfo = categoryService.searchByPage(page,limit,category);
            return Result.success(pageInfo);
        }

    }

    @GetMapping("/{id}")
    public Result get(@PathVariable("id") Integer id){
        Category category = categoryService.getById(id);
        if (category==null) return Result.error("id不存在");
        else return Result.success(category);
    }
}
