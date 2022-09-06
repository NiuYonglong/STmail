package com.situ.stmail.manager.service;

import com.situ.stmail.manager.entity.Admin;

public interface AdminService {
    Admin login(Admin admin) throws Exception;

    Admin modify(Integer id, String oldPassword, String newPassword, String rePassword) throws Exception;

    Admin edit(Admin admin) throws Exception;

}
