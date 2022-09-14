package com.situ.stmail.front.mapper;

import com.situ.stmail.front.entity.OrderDetail;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderDetailMapper {
    @Insert({"<script>"
            ,"insert into order_detail"
            ,"(count,price,goods_id,order_id)"
            ,"value"
            ,"<foreach collection='orderDetails' item='orderDetail' separator=','>"
            ,"(#{orderDetail.count},#{orderDetail.price},#{orderDetail.goodsId},#{orderDetail.orderId})"
            ,"</foreach>"
            ,"</script>"})
    int insertPatch(@Param("orderDetails") List<OrderDetail> orderDetails);

    @Delete({"delete from order_detail where order_id = #{orderId}"})
    int deleteByOrderId(String orderId);

    @Select({"select * from  order_detail where order_id =#{orderId}"})
    @Results({
            @Result(column = "goods_id", property = "goodsId"),
            @Result(column = "goods_id", property = "goods", one = @One(select = "com.situ.stmail.front.mapper.GoodsMapper.selectById"))
    })
    List<OrderDetail> selectByOrderId(String orderId);
}
