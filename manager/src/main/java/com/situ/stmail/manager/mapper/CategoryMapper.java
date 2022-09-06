package com.situ.stmail.manager.mapper;

import com.situ.stmail.manager.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper

public interface CategoryMapper {

    @Insert({"insert into category",
            "(name,dscp, pic, parent_id, recom, status)",
            "value(#{name}, #{dscp}, #{pic}, #{parentId}, #{recom}, #{status})"})
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insert(Category category);

    @Delete("delete from category where id = #{id}")
    int delete(Integer id);

    @Update({"<script> update category ",
            "<set>",
            "<if test = 'name!=null and name.length>0 '> name = #{name},</if>",
            "<if test = 'dscp!=null and dscp.length>0 '> dscp = #{dscp},</if>",
            "<if test = 'pic!=null and pic.length>0 '> pic = #{pic},</if>",
            "<if test = 'parentId != null'> parent_id = #{parentId},</if>",
            "<if test = 'recom != null'> recom = #{recom},</if>",
            "<if test = 'status != null'> status = #{status}</if>",
            "</set>",
            "where id = #{id}",
            "</script>"})
    int update(Category category);

    @Select("select * from category where id = #{id}")
    Category selectById(Integer id);

    @Select({"<script>select * from category",
            "<where>",
            "<if test = 'name != null and name.length > 0'> name like '%${name}%' </if>",
            "<if test = 'parentId != null and parentId != 0'> and parent_id = #{parentId} </if>",
            "<if test = 'parentId != null and parentId == 0'> and parent_id is null</if>",
            "<if test = 'recom != null'> and recom = #{recom} </if>",
            "<if test = 'status != null'> and status = #{status} </if>",
            "</where>",
            "</script>"})
    @Results({
            @Result(column = "parent_id", property = "parent", one = @One(select = "selectById"))
    })
    List<Category> select(Category category);
}
