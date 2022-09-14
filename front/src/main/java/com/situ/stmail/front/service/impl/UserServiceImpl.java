package com.situ.stmail.front.service.impl;

import com.situ.stmail.front.entity.User;
import com.situ.stmail.front.mapper.UserMapper;
import com.situ.stmail.front.service.UserService;
import com.situ.stmail.front.util.MD5Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    private UserMapper userMapper;
    @Override
    public int reg(String username, String password, String rePassword) throws Exception {
        if(username==null||username.length()<2||username.length()>16){
            throw new Exception("用户名不合法");
        }
        if(password==null||password.length()<3||password.length()>16){
            throw new Exception("密码不合法");
        }
        if(!password.equals(rePassword)) throw new Exception("两次输入的密码不一致");

        User user = userMapper.selectByusername(username);
        if(user!=null) throw new Exception("用户名已被占用");

        String salt = UUID.randomUUID().toString().substring(0,8);
        String md5Pwd = MD5Util.getMD5(password+salt);
        user = new User();
        user.setUsername(username);
        user.setPassword(md5Pwd);
        user.setSalt(salt);
        return userMapper.insert(user);
    }

    @Override
    public User login(User user) throws Exception {
        if(user.getUsername()==null||user.getUsername().length()<2||user.getUsername().length()>16){
            throw new Exception("用户名不合法");
        }
//        if(user.getPassword()==null||user.getPassword().length()<3||user.getPayPassword().length()>16){
//            throw new Exception("密码不合法");
//        }
        User sUser = userMapper.selectByusername(user.getUsername());
        if(sUser==null) throw new Exception("用户名不存在");
        String md5Pwd = MD5Util.getMD5(user.getPassword()+sUser.getSalt());
        if(!md5Pwd.equals(sUser.getPassword())) throw new Exception("密码错误");
        if(sUser.getStatus()!=0) throw new Exception("用户被禁用,请联系管理员");
        return sUser;
    }

    @Override
    public int modifyPwd(String username, String oldPassword, String newPassword, String rePassword) throws Exception {
        return 0;
    }

    @Override
    public int edit(User user) throws Exception {
        return 0;
    }

    @Override
    public int modifyPayPwd(String username, String password, String payPassword, String rePassword) throws Exception {
        return 0;
    }
}
