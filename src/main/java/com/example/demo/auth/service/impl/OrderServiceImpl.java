/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.auth.service.impl;

import com.example.demo.auth.service.OrderService;
import com.example.demo.format.MyImageFormat;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class OrderServiceImpl implements OrderService {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;

    @Override
    public void register(Order order) {
        orderRepository.save(order);
    }

    @Override
    public void update(Order order){
        orderRepository.save(order);

    }

    @Override
    public void remove(Order order) {
        orderRepository.delete(order);
    }
    
    @Override
    public Order findOrderByID(int orderID){

        Order order = orderRepository.findOne(orderID);
        if(order!=null)
        {
            order.getMenu().getDishes().forEach(dish->{
                dish.setImage_link_to_show(MyImageFormat.convertImagePath(dish.getImage_path(), dish.getId()));
            });
        }
        
        return order;
    }
    
    @Override
    public List<Order> findAllOrder_InMonth_ByUserID(int year, int month,  int userID) {
       
       List<Order> orders = orderRepository.findAllOrder_InMonth_ByUserID(month, year, userID); 
       if(orders!=null && orders.isEmpty()==false) {
           orders.forEach(order->{
                order.getMenu().getDishes().forEach(dish->{
                dish.setImage_link_to_show(MyImageFormat.convertImagePath(dish.getImage_path(), dish.getId()));
            });
                
            Calendar cal1 = Calendar.getInstance(); 
            cal1.setTime(order.getDay());
            long dayBetween = 
                    ChronoUnit.DAYS.between(
                            cal1.toInstant(), 
                            new Date().toInstant());
            if(7>=dayBetween && dayBetween>=1)
                order.setCanRate(true);
            else
                order.setCanRate(false);
           });
       }
        
        return orders;
    }

    @Override
    public Order findOrder_ByUserID_And_Day(int userID, Date day) {
        
        Order order = orderRepository.findOrderByUserIdAndDay(userID, day);
        if(order!=null)
        {
            order.getMenu().getDishes().forEach(dish->{
                dish.setImage_link_to_show(MyImageFormat.convertImagePath(dish.getImage_path(), dish.getId()));
            });
        }
        
        return order;
    }
    
    
    
}
