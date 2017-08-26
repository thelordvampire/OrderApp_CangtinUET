/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.Order;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Bao
 */
public interface OrderRepository extends JpaRepository<Order, Integer>{
    
    @Query("select o from order o where month(o.day) = ?1 and year(o.day) = ?2 and o.userId = ?3 order by o.day asc")
    List<Order> findAllOrder_InMonth_ByUserID(int month, int year, int userID);
    
    Order findOrderByUserIdAndDay(int userID, Date date);
    
//    Order findOrderById(int orderID);
    
}
