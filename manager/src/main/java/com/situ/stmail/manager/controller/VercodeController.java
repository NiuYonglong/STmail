package com.situ.stmail.manager.controller;

import com.situ.stmail.manager.util.VerCodeUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/vercode")
public class VercodeController {
    @GetMapping
    public void vercode(HttpServletResponse response, HttpSession session){
        String vercode = VerCodeUtil.createVerCode(response);
        session.setAttribute("vercode",vercode);
    }
}
