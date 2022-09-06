package com.situ.stmail.manager.controller.api;

import com.github.pagehelper.PageInfo;
import com.situ.stmail.manager.entity.Goods;
import com.situ.stmail.manager.entity.Result;
import com.situ.stmail.manager.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/api/goods")
public class APIGoodsController {
    @Autowired
    GoodsService goodsService;
    @GetMapping
    public Result get(Integer page,Integer limit,Goods goods){
        if(page==null){
            List<Goods> list = goodsService.search(goods);
            return Result.success(list);
        }else {
            PageInfo pageInfo = goodsService.searchByPage(page, limit, goods);
            return  Result.success(pageInfo);
        }
    }
    @GetMapping("/{id}")
    public Result get(@PathVariable("id") Integer id){
        Goods goods = null;
        try {
            goods = goodsService.getById(id);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        if(goods==null) return Result.error("id不存在");
        else return Result.success(goods);
    }
    @PostMapping
    public Result add(@RequestBody Goods goods){
        try {
            goodsService.add(goods);
            return Result.success();
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
    }
    @DeleteMapping
    public Result remove(Integer id){
        try {
            goodsService.remove(id);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success();
    }
    @PutMapping
    public Result edit(@RequestBody Goods goods){
        try {
            goodsService.edit(goods);
        } catch (Exception e) {
            return Result.error(e.getMessage());
        }
        return Result.success();
    }
}
