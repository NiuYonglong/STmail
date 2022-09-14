package com.situ.stmail.front.controller.api;

import com.situ.stmail.front.entity.Order;
import com.situ.stmail.front.entity.Result;
import com.situ.stmail.front.entity.User;
import com.situ.stmail.front.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/api/user/order")
@AllArgsConstructor
public class APIOrderController {
    private OrderService orderService;
    @PostMapping("/getList")
    public Result getList(HttpSession session){
        User user = (User) session.getAttribute("user");
        List<Order> list = orderService.getByUserId(user.getId());
        return Result.success(list);
    }
}
