package com.situ.stmail.front.controller;

import com.situ.stmail.front.entity.Goods;
import com.situ.stmail.front.service.GoodsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/goods")
@AllArgsConstructor
public class GoodsController {
    private GoodsService goodsService;
    @GetMapping
    public String get(Integer id, Model model){
        Goods goods = goodsService.getById(id);
        model.addAttribute("goods",goods);
        return "goods";
    }
}
