package com.situ.stmail.front.controller.api;

import com.situ.stmail.front.entity.Goods;
import com.situ.stmail.front.entity.Result;
import com.situ.stmail.front.service.GoodsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/goods")
@AllArgsConstructor
public class APIGoodsController {
    private GoodsService goodsService;
    @GetMapping("/search")
    public Result search(Goods goods,String orderBy){
        List<Goods> list = goodsService.search(goods,orderBy);
        return Result.success(list);
    }

}
