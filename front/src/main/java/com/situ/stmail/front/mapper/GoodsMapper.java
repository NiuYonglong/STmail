package com.situ.stmail.front.mapper;

import com.situ.stmail.front.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsMapper {

    //select goods.* from goods
    //

    @Select({"<script>"
            ,"select * from goods"
            ,"inner join category on goods.category_id = category.id"
            ,"inner join category as p on category.parent_id = p.id"
            ,"<where>"
            ,"<if test='where.name!=null and where.name.length>0'> goods.name like '%${where.name}%' </if>"
            ,"<if test='where.categoryId != null'> and"
            ,"(category_id=#{where.categoryId} or category.parent_id = #{where.category})"
            ,"</if>"
            ,"and goods.status = 0 and category.status = 0"
            ,"and p.status = 0"
            ,"</where>"
            ,"<if test = 'orderBy!=null and orderBy.length>0'> order by ${orderBy} </if>"
            ,"</script>"
    })
    @Results(
            id="goods",
            value={
            @Result(column = "id",property = "id",id = true),
            @Result(column = "id", property = "pics",
                    many = @Many(select = "com.situ.stmail.front.mapper.GoodsPicMapper.selectByGoodsId"))
    })
    List<Goods> select(@Param("where") Goods where,@Param("orderBy") String orderBy);

    @Select({"select * from goods where id = ${id}"})
    @ResultMap("goods")
    Goods selectById(Integer id);

    @Update({"update goods"
            ,"set count = #{count}"
            ,"where id = #{id}"
    })
    int update(Goods goods);
}
