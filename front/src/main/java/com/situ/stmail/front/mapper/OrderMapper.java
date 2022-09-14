package com.situ.stmail.front.mapper;

import com.situ.stmail.front.entity.Order;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface OrderMapper {
    @Insert({"insert into `order`"
            ,"(id,user_id,addr_id)"
            ,"value(#{id},#{userId},#{addrId})"})
    int insert(Order order);

    @Delete({"delete from `order` where id = #{id}"})
    int delete(String id);

    @Update({"<script>"
            ,"update `order` "
            ,"<set>"
            ,"<if test='addrId!=null'>addr_id = #{addrId},</if>"
            ,"<if test='status!=null'>status = #{status}</if>"
            ,"</set>"
            ,"where id=#{id}"
            ,"</script>"
    })
    int update(Order order);

    @Select({"select * from `order` where id = #{id}"})
    @Results(id = "order",
    value = {@Result(column = "addr_id",property = "addrId")
            ,@Result(column = "addr_id",property = "addr",one = @One(select = "com.situ.stmail.front.mapper.AddrMapper.selectById") )
            ,@Result(column = "id",property = "id")
            ,@Result(column = "id",property = "orderDetails",many = @Many(select = "com.situ.stmail.front.mapper.OrderDetailMapper.selectByOrderId"))

    }
    )
    Order selectById(String id);

    @Select({"select * from `order` where user_id = #{userId}"
            ,"order by create_time desc"})
    @ResultMap("order")
    List<Order> selectByUserId(Integer userId);
    String[] strs = new String[3];
}
