package com.situ.stmail.manager.mapper;

import com.situ.stmail.manager.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface AdminMapper {
//    int insert(Admin admin);
//    int delete(Integer id);
    @Update({
            "<script>"
            ,"update admin"
            ,"<set>"
            ,"<if test='password!=null and password.length>0'> password=#{password} </if>"
            ,"<if test='salt!=null and salt.length>0'> salt=#{salt} </if>"
            ,"<if test='phone!=null and phone.length>0'> phone=#{phone} </if>"
            ,"<if test='email!=null and email.length>0'> email=#{email} </if>"
            ,"<if test='realname!=null and realname.length>0'> realname=#{realname} </if>"
            ,"<if test='status!=null'> status=#{status} </if>"
            ,"</set>"
            ,"where id = #{id}"
            ,"</script>"
    })
    int update(Admin admin);

    /**
     * 根据用户名查询
     * @param username
     * @return
     */
    @Select(
            "select * from admin where username = #{username}"
    )
    Admin selectByUsername(String username);

    @Select("select * from admin where id = #{id}")
    Admin selectById(Integer id);
}
