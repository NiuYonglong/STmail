package com.situ.stmail.manager.service.impl;

import com.situ.stmail.manager.entity.GoodsPic;
import com.situ.stmail.manager.mapper.GoodsPicMapper;
import com.situ.stmail.manager.service.GoodsPicService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsPicServiceImpl implements GoodsPicService {
    @Resource
    GoodsPicMapper goodsPicMapper;

    @Override
    @Transactional(
            propagation = Propagation.REQUIRED
    )
    public int addPatch(List<GoodsPic> pics) {
        return goodsPicMapper.insert(pics);
    }

    @Override
    public int removeByGoodsId(Integer goodsId) {
        return goodsPicMapper.delete(goodsId);
    }
}
