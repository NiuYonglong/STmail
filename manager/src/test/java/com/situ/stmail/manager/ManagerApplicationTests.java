package com.situ.stmail.manager;

import com.situ.stmail.manager.entity.Category;
import com.situ.stmail.manager.mapper.CategoryMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ManagerApplicationTests {

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void testCategoryMapper(){
        Category category = new Category();
        category.setStatus(1);
        category.setId(2);
        categoryMapper.update(category);
    }

    @Test
    void contextLoads() {
    }

}
