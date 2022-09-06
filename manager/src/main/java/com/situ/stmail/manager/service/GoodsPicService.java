package com.situ.stmail.manager.service;

import com.situ.stmail.manager.entity.GoodsPic;

import java.util.List;

public interface GoodsPicService {
    int addPatch(List<GoodsPic> pics);
    int removeByGoodsId(Integer goodsId);

}
