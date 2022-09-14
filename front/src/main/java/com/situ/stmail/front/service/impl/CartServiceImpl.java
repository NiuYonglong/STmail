package com.situ.stmail.front.service.impl;

import com.situ.stmail.front.entity.Cart;
import com.situ.stmail.front.entity.Goods;
import com.situ.stmail.front.mapper.CartMapper;
import com.situ.stmail.front.service.CartService;
import com.situ.stmail.front.service.GoodsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CartServiceImpl implements CartService {
    private CartMapper cartMapper;
    private GoodsService goodsService;
    @Override
    public int add(Cart cart) throws Exception {
        Goods goods = goodsService.getById(cart.getGoodsId());
        if(goods==null) throw  new Exception("商品id不存在");
        if(goods.getStatus()==1) throw new Exception("商品已下架");
        if(goods.getCount()<1) throw new Exception("商品库存不足");
        Cart sCart = cartMapper.selectByGoodsIdAndUserId(cart.getGoodsId(),cart.getUserId());
        if(sCart==null){
            return cartMapper.insert(cart);
        }else {
            sCart.setCount(sCart.getCount()+1);
            if(goods.getCount()<1) throw new Exception("商品库存不足");
            return cartMapper.update(cart);
        }
    }

    @Override
    public int remove(Integer id,Integer userId) throws Exception {
        Cart cart = cartMapper.selectById(id);
        if(cart.getUserId()!=userId) throw new Exception("权限不足");
        return cartMapper.delete(id);
    }

    @Override
    public int edit(Cart cart,Integer userId) throws Exception {
        Cart sCart = cartMapper.selectById(cart.getId());
        if(sCart.getUserId()!=userId){
            throw new Exception("权限不足");
        }
        Goods goods = goodsService.getById(sCart.getGoodsId());
        if(goods.getStatus()==1){
            remove(sCart.getId(),userId);
            throw new Exception("商品被下架");
        }
        if(goods.getCount()<cart.getCount()){
            cart.setCount(goods.getCount());

        }
        return cartMapper.update(cart);
    }

    @Override
    public List<Cart> getByUserId(Integer userId) {
        return cartMapper.selectByUserId(userId);
    }

    @Override
    public List<Cart> getByIds(Integer[] cartIds, Integer userId) throws Exception {
        if(cartIds==null||cartIds.length==0) throw new Exception("商品列表不能为空");
        List<Cart> carts = cartMapper.selectByIds(cartIds);
        if(carts.size()<cartIds.length){
            throw new Exception("购物车包含了无效id");
        }
        for (Cart cart : carts){
            if(cart.getUserId()!=userId){
                throw new Exception("登录信息已更改");
            }
        }
        return cartMapper.selectByIds(cartIds);
    }
}
