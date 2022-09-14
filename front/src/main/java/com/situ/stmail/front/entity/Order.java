package com.situ.stmail.front.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
public class Order {

    private String id;

    @JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    private Date createTime;
    private Integer userId;
    private Integer addrId;
    private Integer status;
    private Addr addr;
    private List<OrderDetail> orderDetails;
}
