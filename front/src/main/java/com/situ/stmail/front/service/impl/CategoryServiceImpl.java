package com.situ.stmail.front.service.impl;

import com.situ.stmail.front.entity.Category;
import com.situ.stmail.front.mapper.CategoryMapper;
import com.situ.stmail.front.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> getParent() {
        return categoryMapper.selectParent();
    }

}
