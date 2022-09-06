package com.situ.stmail.manager.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class Admin {
    private Integer id;
    private String username;
    private String password;
    private String salt;
    private String phone;
    private String email;
    private String realname;
    private Integer status;
}
