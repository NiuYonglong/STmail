package com.situ.stmail.front.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@ToString

public class Category {
    private Integer id;
    private String name;
    private String desc;
    private Integer parentId;
    private Integer recom;
    private Integer status;

    private Category parent;
    private List<Category> children;
}
