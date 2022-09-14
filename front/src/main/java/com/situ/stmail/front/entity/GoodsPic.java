package com.situ.stmail.front.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString
public class GoodsPic {
    private Integer id;
    private String url;
    private Integer goodsId;
}
