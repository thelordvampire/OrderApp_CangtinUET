/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.auth.service.DailyMenuService;
import com.example.demo.auth.service.OrderService;
import com.example.demo.auth.service.UserService;
import com.example.demo.model.DailyMenu;
import com.example.demo.model.Order;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Bao
 */

@Controller
public class Order2Controller {
    
    @Autowired
    private DailyMenuService menuService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private UserService userService;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping(value = "/order/refresh", method = RequestMethod.POST)
    public String refreshOrder(
            @RequestParam("day") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day,
            Model model) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DailyMenu menu = menuService.findAvaiableMenu_ByDay(java.sql.Date.valueOf(day));
        
        String response="";
        if(menu!=null && auth!=null)
        {
            Order order = orderService.findOrder_ByUserID_And_Day(
                    userService.findIdByEmail(auth.getName()), menu.getDay());
            
            if(order!=null)
            {
//                order.getMenu().getDishes().forEach(dish->{
//                    dish.setImage_link_to_show(MyImageFormat.convertImagePath(dish.getImage_path(), dish.getId()));
//                });
                response= "order/detail ::EditOrderForm";
                model.addAttribute("order", order);
            }
            else
            {
//                menu.getDishes().forEach(dish->{
//                    dish.setImage_link_to_show(MyImageFormat.convertImagePath(dish.getImage_path(), dish.getId()));
//                });
                response = "order/register ::RegisterOrderForm";
                model.addAttribute("menu", menu);
            }
        }
        
        return response;
    }
    
    @RequestMapping(value = "/order/month", method = RequestMethod.GET)
    public String getOrder_InMonth(Model model) {
        Calendar cal = Calendar.getInstance();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
//        logger.info("orders show month: year: "+cal.get(Calendar.YEAR));
//        logger.info("orders show month: month: "+(cal.get(Calendar.MONTH)+1));
//        logger.info("orders show month: user ID: "+userService.findIdByEmail(auth.getName()));
        
        List<Order> orders = orderService.findAllOrder_InMonth_ByUserID(
                cal.get(Calendar.YEAR), 
                cal.get(Calendar.MONTH)+1, 
                userService.findIdByEmail(auth.getName()));
        
        if(orders!=null)
        {
//            logger.info("orders found");
//            logger.info("orders number: "+orders.size());
            int totalPrice = 0;
            for(Order order: orders)
                totalPrice+=order.getPrice();
           
            model.addAttribute("orders", orders);
            model.addAttribute("totalPrice", totalPrice);
            model.addAttribute("month", cal.get(Calendar.MONTH)+1);
            model.addAttribute("year", cal.get(Calendar.YEAR));
        }
        else
            logger.info("orders not found");
        return "order/showMonth";
    }
    
    @RequestMapping(value = "/order/month/next", method = RequestMethod.POST)
    public String getOrder_NextMonth(
            @RequestParam("month") 
            @DateTimeFormat(pattern = "yyyy-M-dd") LocalDate month,
            Model model) {
        
//        System.out.println("tututututut");
    	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if(auth!=null) {
            
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, month.getMonthValue()-1);
            cal.set(Calendar.YEAR, month.getYear());
            cal.add(Calendar.MONTH, 1);
            
            List<Order> orders = orderService.findAllOrder_InMonth_ByUserID(
                                    cal.get(Calendar.YEAR),
                                    cal.get(Calendar.MONTH)+1,
                                    userService.findIdByEmail(auth.getName()));
            
            if(orders!=null && orders.size()>0) {
                int totalPrice = 0;
                for(Order order: orders)
                    totalPrice+=order.getPrice();
                
                model.addAttribute("totalPrice", totalPrice);
                model.addAttribute("orders", orders);
            }
//            System.out.println("tutututu : "+(cal.get(Calendar.MONTH)+1));
            model.addAttribute("month", cal.get(Calendar.MONTH)+1);
            model.addAttribute("year", cal.get(Calendar.YEAR));
            
            return "order/showMonth  ::MonthOrder";
        }
        
        return "redirect:/user/login";
    }
    
    @RequestMapping(value = "/order/month/pre", method = RequestMethod.POST)
    public String getOrder_PreviousMonth(
            @RequestParam("month") @DateTimeFormat(pattern = "yyyy-M-dd") LocalDate month,
            Model model) {
    	
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if(auth!=null) {
            System.out.println("previous month");
            
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.MONTH, month.getMonthValue()-1);
            cal.set(Calendar.YEAR, month.getYear());
            cal.add(Calendar.MONTH, -1);
            
            List<Order> orders = orderService.findAllOrder_InMonth_ByUserID(
                                    cal.get(Calendar.YEAR),
                                    cal.get(Calendar.MONTH)+1,
                                    userService.findIdByEmail(auth.getName()));
            
            if(orders!=null && orders.size()>0) {
                int totalPrice = 0;
                for(Order order: orders)
                    totalPrice+=order.getPrice();
                
                model.addAttribute("totalPrice", totalPrice);
                model.addAttribute("orders", orders);
            }
            model.addAttribute("month", cal.get(Calendar.MONTH)+1);
            model.addAttribute("year", cal.get(Calendar.YEAR));
            
            return "order/showMonth  ::MonthOrder";
        }
        
        return "redirect:/user/login";
    }
    
    @RequestMapping(value = "/order/rate", method = RequestMethod.POST)
    public @ResponseBody String rate(@RequestParam("day") 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate day,
            @RequestParam("rate") int rate, Model model) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if(auth!=null)
        {
            Order order = orderService.findOrder_ByUserID_And_Day(
                    userService.findIdByEmail(auth.getName()), 
                    java.sql.Date.valueOf(day));
            
            if(order!=null)
            {
                order.setRate(rate);
                orderService.update(order);
                
                return "ok";
            }
        }
        
        return "error";
    }
    
    
    @RequestMapping(value = "/order/fast/{orderDay}", method = RequestMethod.GET)
    public String getDish(@PathVariable(value = "orderDay") 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) 
            LocalDate orderDay,
            Model model)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        model.addAttribute("order", 
                orderService.findOrder_ByUserID_And_Day(
                        userService.findIdByEmail(auth.getName()), 
                    java.sql.Date.valueOf(orderDay)));
        return "order/fast_order_detail ::fast_order_detail";
    }
    
}
