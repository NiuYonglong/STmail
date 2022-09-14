package com.situ.stmail.front.controller.api;

import com.situ.stmail.front.entity.Cart;
import com.situ.stmail.front.entity.Result;
import com.situ.stmail.front.entity.User;
import com.situ.stmail.front.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/user/cart")
@AllArgsConstructor
public class APICartController {
    private CartService cartService;
    @GetMapping("/getAll")
    public Result getAll(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Cart> list = cartService.getByUserId(user.getId());

        return Result.success(list);
    }
    @GetMapping("/update")
    public Result update(Cart cart,HttpSession session){
        User user = (User) session.getAttribute("user");
        try {
            cartService.edit(cart,user.getId());
            return Result.success();
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error(e.getMessage());
        }

    }
}
