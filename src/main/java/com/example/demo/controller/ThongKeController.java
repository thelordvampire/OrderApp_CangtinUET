/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.auth.service.DailyMenuService;
import com.example.demo.auth.service.OrderService;
import com.example.demo.model.DailyMenu;
import com.example.demo.model.Order;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Bao
 */

@Controller
public class ThongKeController {
    
    @Autowired
    DailyMenuService menuService;
    
    @Autowired
    OrderService orderService;
    
    @RequestMapping(value = "/admin/menu/thongke", method = RequestMethod.GET)
    public String thongke(Model model)
    {
        model.addAttribute("day", "");
        return "menu/menu_thongke";
    }
    
    @RequestMapping(value = "/admin/menu/thongke", method = RequestMethod.POST)
    public String thongke(Model model, 
            @RequestParam("dayName")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
            pattern = "d-M-yyyy") Date day)
    {
        
//        System.out.println("thong ke day : "+day);
        DailyMenu menu = menuService.findMenuByDay(day);
        int totalPrice=0;
        int rate0 =0,rate1 =0,rate2 =0, rate3 =0,rate4 =0,rate5 = 0;
        if(menu!=null)
        {
            menu.getDishes().forEach(dish->{
                menu.getOrders().forEach(order->{
                    order.getDishes().forEach(dish1->{
                    if(dish.getId()==dish1.getId())
                        dish.increaseCountInMenu();
                    });
                });
            });
            
            for(Order order: menu.getOrders())
            {
                totalPrice+=order.getPrice();
                if(order.getRate()==0)
                    rate0++;
                else if(order.getRate()==1)
                    rate1++;
                else if(order.getRate()==2)
                    rate2++;
                else if(order.getRate()==3)
                    rate3++;
                else if(order.getRate()==4)
                    rate4++;
                else if(order.getRate()==5)
                    rate5++;
            }
            
        }
        
        model.addAttribute("day",day);
        model.addAttribute("menu",menu);
        model.addAttribute("rate0",rate0);
        model.addAttribute("rate1",rate1);
        model.addAttribute("rate2",rate2);
        model.addAttribute("rate3",rate3);
        model.addAttribute("rate4",rate4);
        model.addAttribute("rate5",rate5);
        model.addAttribute("totalPrice", totalPrice);
        return "menu/menu_thongke";
    }
    
}
