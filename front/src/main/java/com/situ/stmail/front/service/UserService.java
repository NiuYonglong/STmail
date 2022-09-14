package com.situ.stmail.front.service;

import com.situ.stmail.front.entity.User;

public interface UserService {
    int reg(String username,String password , String rePassword)throws Exception;
    User login(User user)throws Exception;
    int modifyPwd(String username,String oldPassword,String newPassword,String rePassword)throws Exception;
    int edit(User user)throws Exception;
    int modifyPayPwd(String username,String password,String payPassword,String rePassword)throws Exception;
}
