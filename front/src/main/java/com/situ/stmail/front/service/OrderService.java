package com.situ.stmail.front.service;

import com.situ.stmail.front.entity.Order;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface OrderService {
    @Transactional(
            rollbackFor = Exception.class,
            isolation = Isolation.SERIALIZABLE,
            propagation = Propagation.REQUIRED
    )
    int add(Order order, Integer[] cartIds) throws Exception;

    int remove(String orderId);
    int edit(Order order);
    Order getById(String orderId);
    List<Order> getByUserId(Integer userId);

    int pay(String orderId, String payPwd, Integer userId) throws Exception;
}
