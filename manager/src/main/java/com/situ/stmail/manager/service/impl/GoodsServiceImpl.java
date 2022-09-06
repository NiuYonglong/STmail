package com.situ.stmail.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.stmail.manager.entity.Goods;
import com.situ.stmail.manager.entity.GoodsPic;
import com.situ.stmail.manager.mapper.GoodsMapper;
import com.situ.stmail.manager.mapper.GoodsPicMapper;
import com.situ.stmail.manager.service.GoodsPicService;
import com.situ.stmail.manager.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Resource
    private GoodsMapper goodsMapper;
    @Autowired
    private GoodsPicService goodsPicService ;


    @Override
    @Transactional(
            isolation = Isolation.DEFAULT,
            propagation = Propagation.REQUIRED
            //少了
    )
    public int add(Goods goods) throws Exception {
        if(goods.getName()==null) throw new Exception("名字不能为空");
        if(goods.getDscp()==null) throw new Exception("不能为空");
        if(goods.getPrice()==null) throw new Exception("价格不能为空");
        if(goods.getMarkPrice()==null) throw new Exception("标价不能为空");
        if(goods.getColor()==null) throw new Exception("颜色不能为空");
        if(goods.getVersion()==null) throw new Exception("版本不能为空");
        if(goods.getCount()==null) throw new Exception("数量不能为空");
        if(goods.getRecom()==null) throw new Exception("推荐不能为空");
        if(goods.getCategoryId()==null) throw new Exception("分类号不能为空");
        int code = goodsMapper.insert(goods);
        if(code>0){
            for (GoodsPic pic: goods.getPics()){
                pic.setGoodsId(goods.getId());
            }
            goodsPicService.addPatch(goods.getPics());
        }else {

        }
        return code;
    }

    @Override
    public int remove(Integer id) throws Exception {
        if(goodsMapper.selectById(id)==null){
            throw new Exception("id不存在");
        }
        return goodsMapper.delete(id);
    }

    @Override
    public int edit(Goods goods) throws Exception {
        if(goodsMapper.selectById(goods.getId())==null){
            throw new Exception("id不存在");
        }
        return goodsMapper.update(goods);
    }

    @Override
    public Goods getById(Integer id) throws Exception{
        if(goodsMapper.selectById(id)==null){
            throw new Exception("id不存在");
        }
        return goodsMapper.selectById(id);
    }

    @Override
    public List<Goods> search(Goods goods) {
        return goodsMapper.select(goods);
    }

    @Override
    public PageInfo searchByPage(Integer page, Integer limit, Goods goods) {
        PageHelper.startPage(page,limit);
        List<Goods> list = goodsMapper.select(goods);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
