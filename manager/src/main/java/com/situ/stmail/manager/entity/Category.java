package com.situ.stmail.manager.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Category {
    private Integer id;
    private String name;
    private String dscp;
    private String pic;
    private Integer parentId;
    private Integer recom;
    private Integer status;
    private Category parent;
}
