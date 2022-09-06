package com.situ.stmail.manager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

@Data
@ToString
@NoArgsConstructor
public class Goods {
    private Integer id;
    private String name;
    private String dscp;
    private BigDecimal price;
    private BigDecimal markPrice;
    private String color;
    private String version;
    private Integer count;
    private String content;
    private Integer recom;
    private Integer categoryId;
    private Integer status;
    private Category category;
    private List<GoodsPic> pics;
}
