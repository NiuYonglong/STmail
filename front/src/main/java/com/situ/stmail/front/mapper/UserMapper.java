package com.situ.stmail.front.mapper;

import com.situ.stmail.front.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Mapper
public interface UserMapper {

    @Insert({"insert into user (username,password,salt) value (#{username},#{password},#{salt})"})
    int insert(User user);

    @Update({"<script>"
            ,"update user"
            ,"<set >"
            ,"<if test='password!=null and password.length>0'>password=#{password},</if>"
            ,"<if test='phone!=null and phone.length>0'>phone=#{phone},</if>"
            ,"<if test='email!=null and email.length>0'>email=#{email},</if>"
            ,"<if test='realname!=null and realname.length>0'>realname=#{realname},</if>"
            ,"<if test='sex!=null and sex.length>0'>sex=#{sex},</if>"
            ,"<if test='birthday!=null and birthday.length>0'>birthday=#{birthday},</if>"
            ,"<if test='idcard!=null and idcard.length>0'>idcard=#{idcard},</if>"
            ,"<if test='money!=null'>money=#{money},</if>"
            ,"<if test='payPassword!=null and payPassword.length>0'>pay_password=#{payPassword}</if>"
            ,"</set>"
            ,"where id = #{id}"
            ,"</script>"})
    int update(User user);

    @Select("select * from user where username=#{username}")
    User selectByusername(String username);

    @Select("select * from user where id = #{id}")
    User selectById(Integer userId);
}
