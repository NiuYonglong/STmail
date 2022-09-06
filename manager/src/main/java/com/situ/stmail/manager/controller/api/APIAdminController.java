package com.situ.stmail.manager.controller.api;

import com.situ.stmail.manager.entity.Admin;
import com.situ.stmail.manager.entity.Result;
import com.situ.stmail.manager.service.AdminService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/admin")
@AllArgsConstructor
public class APIAdminController {

    private AdminService adminService;

    @PostMapping("/login")
    public Result login(Admin admin, String vercode, HttpSession session){
        String sVercode = (String) session.getAttribute("vercode");
        if(sVercode == null || !sVercode.equals(vercode.toUpperCase())){
            return Result.error("验证码错误");
        }
        try {
            admin = adminService.login(admin);
            admin.setPassword(null);
            admin.setSalt(null);
            session.setAttribute("admin",admin);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @GetMapping("/logout")
    public Result logout(HttpSession session){
        session.invalidate();
        return Result.success();
    }

    @PostMapping("/modifyPwd")
    public Result modifyPwd(String oldPassword , String newPassword, String rePassword, HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        try {
            adminService.modify(admin.getId(),oldPassword,newPassword,rePassword);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }
    }

    @PutMapping
    public Result edit(Admin admin,HttpSession session){
        admin.setId((Integer) session.getAttribute("id"));
        try {
            adminService.edit(admin);
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }

    }
    @GetMapping("/getLogin")
    public Result getLogin(HttpSession session){
        Admin admin = (Admin) session.getAttribute("admin");
        if(admin!=null){
            return Result.success(admin);
        }else {
            return Result.error("未登录");
        }
    }
}
