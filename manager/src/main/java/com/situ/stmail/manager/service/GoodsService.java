package com.situ.stmail.manager.service;

import com.github.pagehelper.PageInfo;
import com.situ.stmail.manager.entity.Goods;

import java.util.List;

public interface GoodsService {
    int add(Goods goods) throws Exception;
    int remove(Integer id) throws Exception;
    int edit(Goods goods) throws Exception;
    Goods getById(Integer id) throws Exception;
    List<Goods> search(Goods goods);

    PageInfo searchByPage(Integer page, Integer limit, Goods goods);
}
