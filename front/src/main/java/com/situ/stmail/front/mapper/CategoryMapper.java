package com.situ.stmail.front.mapper;

import com.situ.stmail.front.entity.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select({"select * from category where parent_id is null and status = 0"})
    @Results({
            @Result(column = "id", property = "children",many = @Many(select = "selectByParentId"))
    })
    List<Category> selectParent();

    @Select({"select * from category where parent_id = #{parentId} and status = 0"})
    List<Category> selectByParentId(Integer parentId);
}
