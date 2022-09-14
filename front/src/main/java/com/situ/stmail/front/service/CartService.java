package com.situ.stmail.front.service;

import com.situ.stmail.front.entity.Cart;

import java.util.List;

public interface CartService {

    int add(Cart cart) throws Exception;
    int remove(Integer id,Integer userId) throws Exception;
    int edit(Cart cart,Integer userId) throws Exception;
    List<Cart> getByUserId(Integer userId);

    List<Cart> getByIds(Integer[] cartIds,Integer userId) throws Exception;
}
