package com.situ.stmail.front.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@ToString
public class OrderDetail {
    private Integer id;
    private Integer count;
    private BigDecimal price;
    private Integer goodsId;
    private String orderId;
    private Goods goods;
}
