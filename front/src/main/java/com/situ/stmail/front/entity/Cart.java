package com.situ.stmail.front.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class Cart {
    private Integer id;
    private Integer count;
    private Integer goodsId;
    private Integer userId;
    private Goods goods;
}
