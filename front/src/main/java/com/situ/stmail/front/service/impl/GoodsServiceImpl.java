package com.situ.stmail.front.service.impl;

import com.situ.stmail.front.entity.Goods;
import com.situ.stmail.front.mapper.GoodsMapper;
import com.situ.stmail.front.service.GoodsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GoodsServiceImpl implements GoodsService {
    private GoodsMapper goodsMapper;
    @Override
    public Goods getById(Integer id) {
        return goodsMapper.selectById(id);
    }

    @Override
    public List<Goods> search(Goods where, String orderBy) {
        return goodsMapper.select(where,orderBy);
    }
}
