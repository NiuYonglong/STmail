package com.situ.stmail.manager.mapper;

import com.situ.stmail.manager.entity.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsMapper {

    @Insert({"insert into goods"
            ,"(name,dscp,price,mark_price,color,version,count,content,recom,category_id)"
            ,"value(#{name},#{dscp},#{price},#{markPrice},#{color},#{version},#{count},#{content},#{recom},#{categoryId})"
            })
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")//没听懂
    int insert(Goods goods);

    @Delete({"delete from goods"
            ,"where id = #{id}"})
    int delete(Integer id);

    @Update({"<script>update goods"
            ,"<set>"
            ,"<if test = 'name!=null and name.length>0' > name = #{name},</if>"
            ,"<if test = 'dscp!=null and dscp.length>0' >  dscp = #{dscp},</if>"
            ,"<if test = 'price!=null and price>0' >  price = #{price},</if>"
            ,"<if test = 'markPrice!=null and markPrice>0' >  mark_price = #{markPrice},</if>"
            ,"<if test = 'color!=null' >  color = #{color},</if>"
            ,"<if test = 'version!=null' >  version = #{version},</if>"
            ,"<if test = 'count!=null and count>=0' >  count = #{count},</if>"
            ,"<if test = 'content!=null' >  content = #{content},</if>"
            ,"<if test = 'recom!=null' >  recom = #{recom},</if>"
            ,"<if test = 'categoryId!=null' >  category_id = #{categoryId},</if>"
            ,"<if test = 'status!=null' >  status = #{status},</if>"
            ,"</set>"
            ,"where id = #{id}"
            ,"</script>"})
    int update(Goods goods);

    @Select({"select * from goods where id = #{id}"})
    @Results({//没听懂
            @Result(column = "id",property = "id",id = true),
            @Result(column = "id",property = "pics",many = @Many(select = "com.situ.stmail.manager.mapper.GoodsPicMapper.selectByGoodsId"))
    })
    Goods selectById(Integer id);

    //待完善
    @Select({"<script>select * from goods"
            ,"<where>"
            ,"<if test = 'name!=null and name.length>0'> name like '%${name}%' </if>"
            ,"<if test = 'dscp!=null and dscp.length>0'> and dscp like '%${dscp}%' </if>"
            ,"<if test = 'color!=null and color.length>0'> and color like '%${color}%' </if>"
            ,"<if test = 'version!=null and version.length>0'> and version = #{version} </if>"
            ,"<if test = 'recom != null'> and recom = #{recom} </if>"
            ,"<if test = 'status != null'> and status = #{status} </if>"
            ,"<if test = 'categoryId != null'> and category_id = #{categoryId} </if>"
            ,"</where>"
            ,"</script>"})
    @Results({//没听懂
            @Result(column = "id",property = "id",id = true),
            @Result(column = "category_id",property = "category",one = @One(select = "com.situ.stmail.manager.mapper.CategoryMapper.selectById")),
            @Result(column = "id",property = "pics",many = @Many(select = "com.situ.stmail.manager.mapper.GoodsPicMapper.selectByGoodsId"))
    })
    List<Goods> select(Goods goods);
}
