package com.situ.stmail.front.mapper;

import com.situ.stmail.front.entity.Addr;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AddrMapper {
    @Insert({"insert into addr"
            ,"(contact,phone,province,city,county,town,detail,user_id)"
            ,"value(#{contact},#{phone},#{province},#{city},#{county},#{town},#{detail},#{userId}"})
    int insert(Addr addr);

    @Delete({"delete from addr "
            ,"where id = #{id}"
            ,""})
    int delete(Integer id);

    @Update({"update addr set contact = #{contact},phone=#{phone},province=#{province},"
            ,"city=#{city},county=#{county},town=#{town},detail=#{detail},status=#{status}"})
    int update(Addr addr);

    @Select({"select * from addr where user_id = #{userId}"})
    List<Addr> selectByUserId(Integer userId);

    @Select({"select * from addr where id = #{id}"})
    Addr selectById(Integer id);
}
