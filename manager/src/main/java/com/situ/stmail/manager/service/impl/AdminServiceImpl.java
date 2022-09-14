package com.situ.stmail.manager.service.impl;

import com.situ.stmail.manager.entity.Admin;
import com.situ.stmail.manager.mapper.AdminMapper;
import com.situ.stmail.manager.service.AdminService;
import com.situ.stmail.manager.util.MD5Util;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AdminServiceImpl implements AdminService {

    private AdminMapper adminMapper;
    @Override
    public Admin login(Admin admin) throws Exception {
        if(admin.getUsername()==null||admin.getUsername().length()<2||admin.getUsername().length()>16){
            throw new Exception("用户名格式错误");
        }
        if(admin.getPassword()==null||admin.getPassword().length()<3||admin.getPassword().length()>32){
            throw new Exception("密码必须是3-32位的字符串");
        }
        Admin sAdmin = adminMapper.selectByUsername(admin.getUsername());
        if(sAdmin==null){
            throw  new Exception("用户名不存在");
        }
        String password = admin.getPassword();
        password = MD5Util.getMD5(password+sAdmin.getSalt());
        //System.out.println(MD5Util.getMD5(sAdmin.getPassword()+sAdmin.getSalt()));
        if(!password.equals(sAdmin.getPassword())){
            throw new Exception("密码错误");
        }
        if(sAdmin.getStatus()==1){
            throw new Exception("用户被禁用");
        }
        return sAdmin;
    }

    @Override
    public Admin modify(Integer id, String oldPassword, String newPassword, String rePassword) throws Exception {
        if(oldPassword==null||oldPassword.length()<3||oldPassword.length()>32){
            throw new Exception("旧密码格式不正确");
        }
        if(newPassword==null||newPassword.length()<3||newPassword.length()>32){
            throw new Exception("旧密码格式不正确");
        }
        if(!rePassword.equals(newPassword)){
            throw new Exception("两次输入的密码不一致");
        }
        Admin admin = adminMapper.selectById(id);
        String md5Password = MD5Util.getMD5(oldPassword+admin.getSalt());
        if(!md5Password.equals(admin.getPassword())){
            throw new Exception("旧密码不正确");
        }
        admin.setPassword(MD5Util.getMD5(newPassword+admin.getSalt()));
        adminMapper.update(admin);
        return admin;
    }

    @Override
    public Admin edit(Admin admin) throws Exception {

        if(admin.getUsername()==null||admin.getUsername().length()<3||admin.getUsername().length()>16){
            throw new Exception("用户名格式错误");
        }
        adminMapper.update(admin);
        return null;
    }
}
