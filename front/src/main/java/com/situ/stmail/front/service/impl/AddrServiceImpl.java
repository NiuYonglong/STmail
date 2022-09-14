package com.situ.stmail.front.service.impl;

import com.situ.stmail.front.entity.Addr;
import com.situ.stmail.front.mapper.AddrMapper;
import com.situ.stmail.front.service.AddrService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AddrServiceImpl implements AddrService {
    private AddrMapper addrMapper;
    @Override
    public int add(Addr addr) {
        return addrMapper.insert(addr);
    }

    @Override
    public int remove(Integer id) {
        return addrMapper.delete(id);
    }

    @Override
    public int edit(Addr addr) {
        return addrMapper.update(addr);
    }

    @Override
    public List<Addr> getByUserId(Integer userId) {
        return addrMapper.selectByUserId(userId);
    }

    @Override
    public Addr getById(Integer id) {
        return addrMapper.selectById(id);
    }
}
