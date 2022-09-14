package com.situ.stmail.front.mapper;

import com.situ.stmail.front.entity.Cart;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CartMapper {

    @Insert({"insert into cart"
            ,"(count,goods_id,user_id)"
            ,"value(#{count},#{goodsId},#{userId})"})
    int insert(Cart cart);

    @Delete({"delete from cart"
            ,"where id = #{id}"})
    int delete(Integer id);

    @Update({"update cart"
            ,"set count = #{count}"
            ,"where id = #{id}"})
    int update(Cart cart);

    @Select({"select * from cart"
            ,"where user_id = #{userId}"})
    @Results(id = "cart"
            ,value = {
            @Result(column = "goods_id",property = "goods",one = @One(select = "com.situ.stmail.front.mapper.GoodsMapper.selectById"))
    })
    List<Cart> selectByUserId(Integer userId);

    @Select({"select * from cart where id = #{id}"})
    Cart selectById(Integer id);

    @Select({"select * from cart where goods_id = #{goodsId} and user_id = #{userId}"})
    Cart selectByGoodsIdAndUserId(@Param("goodsId") Integer goodsId, @Param("userId") Integer userId);

    @Select({"<script>"
            ,"select * from cart"
            ,"where id in"
            ,"<foreach collection='cartIds' item='cartId' open='(' close=')' separator=','>"
            ,"#{cartId}"
            ,"</foreach>"
            ,"</script>"})
    @ResultMap("cart")
    List<Cart> selectByIds(@Param("cartIds") Integer[] cartIds);
}
