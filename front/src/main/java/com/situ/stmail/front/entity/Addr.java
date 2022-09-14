package com.situ.stmail.front.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Addr {
    private Integer id;
    private String contact;
    private String phone;
    private String province;
    private String city;
    private String county;
    private String town;
    private String detail;
    private Integer userId;
    private Integer status;
}
