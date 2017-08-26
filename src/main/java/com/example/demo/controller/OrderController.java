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
import com.example.demo.model.Dish;
import com.example.demo.model.Order;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.demo.auth.validator.OrderValidate;
import javax.servlet.http.HttpServletResponse;


@Controller
public class OrderController { 
        
    @Autowired
    private DailyMenuService menuService;
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private UserService userService;
    
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @RequestMapping(value = "/home.html", method = RequestMethod.GET)
    public String index(Model model) 
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DailyMenu menu = menuService.findAvaiableMenu_ByNextDay(Calendar.getInstance().getTime());
        Order order = orderService.findOrder_ByUserID_And_Day(userService.findIdByEmail(auth.getName()), menu.getDay());

//        if(menu!=null)
//        {
//            menu.getDishes().forEach(dish->{
//                dish.setImage_link_to_show(MyImageFormat.convertImagePath(dish.getImage_path(), dish.getId()));
//            });
//        }
        
        
//        if(order!=null)
//        {
//            order.getDishes().forEach(dish->{
//                dish.setImage_link_to_show(MyImageFormat.convertImagePath(dish.getImage_path(), dish.getId()));
//            });
//        }
        
        model.addAttribute("menu", menu);
        model.addAttribute("order", order);
        
        return "home";
    }
    
    @RequestMapping(value = "/order/add", method = RequestMethod.POST)
    public ResponseEntity<Object> add(
            @RequestParam("day") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day, 
            @RequestParam("price") String price,
            @RequestParam(value = "dishes[]", required = false) java.util.List<Integer> dishIDs, 
            @RequestParam("infor") String infor, HttpServletResponse a) {
        
        HashMap<String, String> errors = OrderValidate.validate(price, dishIDs, infor);
        if(errors.isEmpty())
        {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            Order order = new Order();
            order.setDay(java.sql.Date.valueOf(day));
            order.setPrice(Integer.parseInt(price));
            order.setInfor(infor);
            order.setRate(0);
            order.setDishes(Dish.toListDish(dishIDs));
            order.setUserId(userService.findIdByEmail(auth.getName()));
            orderService.register(order);
            
            HashMap<String, String> success = new HashMap<>();
            success.put("success.message", "Bạn đã đăng ký thành công");
            return new ResponseEntity<>(success,HttpStatus.OK);
        }
        
        return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/order/update", method = RequestMethod.POST)
    public ResponseEntity<Object> update(
//            @RequestParam("orderID") int orderID,
            @RequestParam("day") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate day, 
            @RequestParam("price") String price,
            @RequestParam(value = "dishes[]", required = false) java.util.List<Integer> dishIDs, 
            @RequestParam("infor") String infor) {

        //kiem tra dieu kien
//        System.out.println("order update");
//        if(dishIDs!=null)
//            System.out.println(Arrays.toString(dishIDs.toArray()));


        HashMap<String, String> errors = OrderValidate.validate(price, dishIDs, infor);
        if(errors.isEmpty())
        {        
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            if(auth!=null)
            {
                Order order = orderService.findOrder_ByUserID_And_Day(
                    userService.findIdByEmail(auth.getName()), 
                    java.sql.Date.valueOf(day));
                
//                Order order = new Order();
//                order.setId(orderID);
//                order.setDay(java.sql.Date.valueOf(day));
                order.setPrice(Integer.parseInt(price));
                order.setInfor(infor);
                order.setDishes(Dish.toListDish(dishIDs));
//                order.setUserId(userService.findIdByEmail(auth.getName()));
                orderService.update(order);
                
                HashMap<String, String> success = new HashMap<>();
                success.put("success.message", "Bạn đã sửa thành công");
                return new ResponseEntity<>(success,HttpStatus.OK);
            }
        }
        
        return new ResponseEntity<>(errors,HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/order/{day}", method = RequestMethod.GET)
    public String getDetailOrder(
            @PathVariable @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate day,
            Model model) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        
        if(auth!=null) {
            
            Order order = orderService.findOrder_ByUserID_And_Day(
                    userService.findIdByEmail(auth.getName()), 
                    java.sql.Date.valueOf(day));
            if(order!=null)
            {
//                logger.info("orders found");
    //            List<Integer> dishIDs = new ArrayList<>();
    //            int 
    //            
    //            order.getDishes().forEach(dish->{
    //                dishIDs.add(dish.getId());
    //                
    //                System.out.println("dish id: "+dish.getId());
    //            });

                model.addAttribute("order", order);
    //            model.addAttribute("dishIDs", dishIDs);
    //            model.
            }
//            else
//                logger.info("order not found");
            
            return "order/detail";
        }
        
        return "redirect:/user/login";
        
        
        
        
        
    }
    
    @RequestMapping(value = "/order/delete", method = RequestMethod.POST)
    public ResponseEntity<String> delete(@RequestParam("day") @DateTimeFormat(iso=DateTimeFormat.ISO.DATE) LocalDate day)
    {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth!=null)
        {
            
            Order order = orderService.findOrder_ByUserID_And_Day(
                    userService.findIdByEmail(auth.getName()), 
                    java.sql.Date.valueOf(day));
            orderService.remove(order);
            
            return new ResponseEntity<>("thanh cong", HttpStatus.OK);
        }
        return new ResponseEntity<>("chua dang nhap", HttpStatus.BAD_REQUEST);
    }
    
    @RequestMapping(value = "/order/next", method = RequestMethod.POST)
    public String getOrder_NextDay(
            @RequestParam("day") 
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
                    pattern = "d-M-yyyy") LocalDate day,
            Model model) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DailyMenu menu = menuService.findAvaiableMenu_ByNextDay(java.sql.Date.valueOf(day));
        
        String response="";
        if(menu!=null && auth!=null)
        {
            Order order = orderService.findOrder_ByUserID_And_Day(
                    userService.findIdByEmail(auth.getName()), menu.getDay());
            
            if(order!=null)
            {
//                return new ResponseEntity<>(order, HttpStatus.OK);
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
//                return new ResponseEntity<>(menu, HttpStatus.OK);
                response = "order/register ::RegisterOrderForm";
                model.addAttribute("menu", menu);
            }
        }
//        if(menu!=null)
//        {
//            System.out.println("get Order next day: ");
//            System.out.println(menu.toString());
//            menu.getDishes().forEach(dish->{
//                System.out.println("order dish: "+dish.toString());
//            });
//        }
                
//        return new ResponseEntity<>("Không có gì cả", HttpStatus.OK);
            
//        System.out.println("response: 11"+response);
        return response;
    }
    
    
    
    @RequestMapping(value = "/order/pre", method = RequestMethod.POST)
    public String getOrder_PreviousDay(
            @RequestParam("day") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE,
                    pattern = "d-M-yyyy") LocalDate day,
            Model model) {
        
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        DailyMenu menu = menuService.findAvaiableMenu_ByPreviousDay(java.sql.Date.valueOf(day));
        
        String response="";
        if(menu!=null && auth!=null && menu.getDay().after(Calendar.getInstance().getTime()))
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
    
}
