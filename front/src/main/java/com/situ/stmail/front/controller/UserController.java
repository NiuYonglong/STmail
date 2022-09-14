package com.situ.stmail.front.controller;

import com.situ.stmail.front.entity.User;
import com.situ.stmail.front.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {
    private UserService userService;
    @GetMapping("/reg")
    public String reg(){
        return "user/reg";
    }
    @PostMapping("/reg")
    public String reg(String username, String password, @RequestParam("repassword") String rePassword, Model model){
        try {
            userService.reg(username,password,rePassword);
            return "redirect:/user/login";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
            return "/user/reg";
        }
    }
    @GetMapping("/login")
    public String login(){
        return "user/login";
    }
    @PostMapping("/login")
    public String login(User user, HttpSession session,Model model){
        try {
            user = userService.login(user);
            session.setAttribute("user",user);
            return "redirect:/";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
            return "user/login";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

}
