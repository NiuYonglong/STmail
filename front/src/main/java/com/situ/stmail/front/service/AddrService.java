package com.situ.stmail.front.service;

import com.situ.stmail.front.entity.Addr;

import java.util.List;

public interface AddrService {
    int add(Addr addr);
    int remove(Integer id);
    int edit(Addr addr);
    List<Addr> getByUserId(Integer userId);
    Addr getById(Integer id);
}
