package com.situ.stmail.front.controller;

import com.situ.stmail.front.entity.Cart;
import com.situ.stmail.front.entity.User;
import com.situ.stmail.front.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/cart")
@AllArgsConstructor
public class CartController {
    private CartService cartService;
    @GetMapping("/add")
    public String add(Integer id, HttpSession session, Model model){
        Cart cart = new Cart();
        cart.setGoodsId(id);
        cart.setCount(1);
        cart.setUserId(((User) session.getAttribute("user")).getId());
        try {
            cartService.add(cart);
            return "redirect:/user/cart";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
            return "user/error";
        }
    }

    @GetMapping
    public String cart(){
        return "user/cart";
    }
}
