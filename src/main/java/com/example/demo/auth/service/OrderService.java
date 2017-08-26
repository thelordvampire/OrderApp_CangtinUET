/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.auth.service;

import com.example.demo.model.Order;
import java.time.LocalDate;
import java.util.List;
import java.util.Date;

/**
 *
 * @author Bao
 */
public interface OrderService {
    
    void register(Order order);
    void update(Order order);
    void remove(Order order);

    Order findOrderByID(int orderID);
    
    List<Order> findAllOrder_InMonth_ByUserID(int year, int month, int userID);
    
    Order findOrder_ByUserID_And_Day(int userID, Date day);

   
    
    
}
