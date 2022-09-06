package com.situ.stmail.manager.service;

import com.github.pagehelper.PageInfo;
import com.situ.stmail.manager.entity.Category;

import java.util.List;


public interface CategoryService {
    int add(Category category) throws Exception;
    int remove(int id) throws Exception;
    int edit(Category category) throws Exception;
    Category getById(Integer id);
    List<Category> search(Category category);

    PageInfo searchByPage(Integer page, Integer limit, Category category);
}
