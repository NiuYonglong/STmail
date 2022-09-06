package com.situ.stmail.manager.mapper;

import com.situ.stmail.manager.entity.GoodsPic;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface GoodsPicMapper {
    @Insert({"<script>"
            ,"insert into goods_pic"
            ,"(url,goods_id)"
            ,"value"
            ,"<foreach collection = 'pics' item='pic' separator=','>"
            ,"(#{pic.url},#{pic.goodsId})"
            ,"</foreach>"
            ,"</script>"})
    int insert(@Param("pics") List<GoodsPic> pics);

    @Delete({"delete from goods_pic"
            ,"where goods_id = #{id}"})
    int delete(Integer goodsId);

    @Update({"<script>update goods_pic"
            ,"<set>"
            ,"<if test = 'url!=null and url.length>0'> url=#{url} </if>"
            ,"<if test = 'goodsId!=null'> goods_id = #{goodId} </if>"
            ,"</set>"
            ,"where id = #{id}"
            ,"</script>"})
    int update(GoodsPic goodsPic);

    @Select({"select * from goods_pic where goods_id = #{goodsId}"})
    GoodsPic selectById(Integer goodsId);

    @Select({"select * from goods_pic where goods_id = #{goodsId}"})
    List<GoodsPic> selectByGoodsId(Integer id);
}
