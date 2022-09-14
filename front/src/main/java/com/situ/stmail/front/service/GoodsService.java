package com.situ.stmail.front.service;

import com.situ.stmail.front.entity.Goods;

import java.util.List;

public interface GoodsService {

    Goods getById(Integer id);

    List<Goods> search(Goods where,String orderBy);

}
