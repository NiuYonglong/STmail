package com.situ.stmail.front.service.impl;

import com.situ.stmail.front.entity.*;
import com.situ.stmail.front.mapper.*;
import com.situ.stmail.front.service.OrderService;
import com.situ.stmail.front.util.MD5Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private CartMapper cartMapper;
    private OrderMapper orderMapper;
    private OrderDetailMapper orderDetailMapper;
    private AddrMapper addrMapper;
    private GoodsMapper goodsMapper;
    private UserMapper userMapper;

    @Override
    @Transactional(
            rollbackFor = Exception.class,
            isolation = Isolation.SERIALIZABLE,
            propagation = Propagation.REQUIRED
    )
    public int add(Order order,Integer[] cartIds) throws Exception {
        order.setId(UUID.randomUUID().toString());
        Addr addr = addrMapper.selectById(order.getAddrId());
        if(addr.getUserId() != order.getUserId()) {
            throw new Exception("当前地址不匹配，请重新生成订单");
        }
        int code = orderMapper.insert(order);
        if(code<=0){
            throw new Exception("订单保存失败！！");
        }
        List<Cart> carts = cartMapper.selectByIds(cartIds);
        if(carts.size()!=cartIds.length){
            throw new Exception("购物车数据不匹配！！");
        }
        for (Cart cart :carts){
            if(cart.getUserId()!=order.getUserId()){
                throw new Exception("购物车数据与用户不匹配");
            }
            if(cart.getCount() > cart.getGoods().getCount()){
                throw new Exception("库存不足");
            }
            if(cart.getGoods().getStatus()==1){
                throw new Exception("商品已经下架!");
            }
            cart.getGoods().setCount(cart.getGoods().getCount() - cart.getCount());

            code = goodsMapper.update(cart.getGoods());
            if(code<=0){
                throw new Exception("商品库存扣除失败");
            }
        }

        List<OrderDetail> orderDetails = new ArrayList<>();
        for(Cart cart:carts){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrderId(order.getId());
            orderDetail.setCount(cart.getCount());
            orderDetail.setGoodsId(cart.getGoods().getId());
            orderDetail.setPrice(cart.getGoods().getPrice());

            orderDetails.add(orderDetail);
        }
        code = orderDetailMapper.insertPatch(orderDetails);
        if(code!= cartIds.length){
            throw new Exception("订单详情写入失败！！");
        }
        for(Cart cart : carts){
            cartMapper.delete(cart.getId());
        }
        return 1;
    }

    @Override
    public int remove(String orderId) {
        return orderMapper.delete(orderId);
    }

    @Override
    public int edit(Order order) {
        return orderMapper.update(order);
    }

    @Override
    public Order getById(String orderId) {
        return orderMapper.selectById(orderId);
    }

    @Override
    public List<Order> getByUserId(Integer userId) {
        return orderMapper.selectByUserId(userId);
    }

    @Transactional(
            rollbackFor = Exception.class,
            isolation = Isolation.SERIALIZABLE,
            propagation = Propagation.REQUIRED
    )
    @Override
    public int pay(String orderId, String payPwd, Integer userId) throws Exception {
        Order order = orderMapper.selectById(orderId);
        if(order == null)throw new Exception("订单不存在!");
        if(order.getStatus()!=0) throw new Exception("订单状态异常");
        if(order.getUserId()!=userId) throw new Exception("登录信息发生改变");
        BigDecimal sum = new BigDecimal(0);
        for(OrderDetail orderDetail: order.getOrderDetails()){
            sum = sum.add(orderDetail.getPrice().multiply(new BigDecimal(orderDetail.getCount())));
        }
        User user = userMapper.selectById(userId);
        if(sum.compareTo(user.getMoney())==1){
            throw new Exception("余额不足");
        }
        if(user.getPayPassword()==null){
            throw new Exception("未设置支付密码");
        }
        String md5PayPwd = MD5Util.getMD5(payPwd+user.getSalt());
        System.out.println(md5PayPwd);
        if(!user.getPayPassword().equals(md5PayPwd)){
            throw new Exception("支付密码错误");
        }
        User tempUser = new User();
        tempUser.setMoney(user.getMoney().subtract(sum));
        tempUser.setId(userId);
        //user.setMoney(user.getMoney().subtract(sum));
        int code = userMapper.update(tempUser);
        if(code != 1){
            throw new Exception("扣款失败");
        }
        order.setStatus(1);
        code = orderMapper.update(order);
        if(code!=1){
            throw new Exception("订单状态修改失败");
        }

        return 1;
    }
}
