package com.situ.stmail.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.situ.stmail.manager.entity.Category;
import com.situ.stmail.manager.mapper.CategoryMapper;
import com.situ.stmail.manager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Override
    public int add(Category category) throws Exception {
        if(category.getName() == null || category.getName().length() == 0 ){
            throw new Exception("分类名不能为空");
        }
        if(category.getRecom() == null){
            throw new Exception("推荐不能为空");
        }
        return categoryMapper.insert(category);
    }

    @Override
    public int remove(int id) throws Exception {
        if (categoryMapper.selectById(id)==null) throw new Exception("id不存在");
        return categoryMapper.delete(id);
    }

    @Override
    public int edit(Category category) throws Exception {
        if(categoryMapper.selectById(category.getId())==null){
            throw new Exception("id不存在");
        }

        return categoryMapper.update(category);
    }

    @Override
    public Category getById(Integer id) {
        return categoryMapper.selectById(id);
    }

    @Override
    public List<Category> search(Category category) {
        return categoryMapper.select(category);
    }

    @Override
    public PageInfo searchByPage(Integer page, Integer limit, Category category) {
        PageHelper.startPage(page,limit);
        List<Category> list = categoryMapper.select(category);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
