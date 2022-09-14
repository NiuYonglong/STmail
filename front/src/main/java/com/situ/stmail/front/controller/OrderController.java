package com.situ.stmail.front.controller;

import com.situ.stmail.front.entity.Addr;
import com.situ.stmail.front.entity.Cart;
import com.situ.stmail.front.entity.Order;
import com.situ.stmail.front.entity.User;
import com.situ.stmail.front.service.AddrService;
import com.situ.stmail.front.service.CartService;
import com.situ.stmail.front.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/user/order")
@AllArgsConstructor
public class OrderController {
    private AddrService addrService;
    private CartService cartService;
    private OrderService orderService;
    @PostMapping("/confirm")
    public String confirm(HttpSession session, Model model, Integer... cartId){
        User user = (User) session.getAttribute("user");
        List<Addr> addresses = addrService.getByUserId(user.getId());
        model.addAttribute("addresses",addresses);
        try {
            List<Cart> carts = cartService.getByIds(cartId, user.getId());
            model.addAttribute("carts",carts);
            return "user/orderConfirm";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
            return "user/error";
        }
    }
    @GetMapping("/add")
    public String add(HttpSession session,Model model,Integer addressId,Integer... cartId){
        User user = (User) session.getAttribute("user");

        Order order = new Order();
        order.setAddrId(addressId);
        order.setUserId(user.getId());
        try {
            orderService.add(order,cartId);

            return "redirect:/user/order/pay?id="+order.getId();
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
            return "user/error";
        }
    }

    @GetMapping("/pay")
    public String pay(String id,Model model){
        Order order = orderService.getById(id);
        model.addAttribute("order",order);
        return "user/pay";
    }
    @PostMapping("/pay")
    public String pay(String id,String payPwd,HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        try {
            orderService.pay(id,payPwd,user.getId() );
            return "redirect:user/order/list";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("error",e.getMessage());
            return "user/error";
        }
    }
    @GetMapping("/list")
    public String list(){
        return "user/orderList";
    }
}
