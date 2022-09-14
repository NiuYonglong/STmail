package com.situ.stmail.front.mapper;

import com.situ.stmail.front.entity.GoodsPic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface GoodsPicMapper {

    @Select({"select * from goods_pic where goods_id = #{goodsId}"})
    List<GoodsPic> selectByGoodsId(Integer goodsId);
}
