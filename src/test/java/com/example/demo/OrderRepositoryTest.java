/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.format.MyDateFormat;
import com.example.demo.model.Order;
import com.example.demo.model.User;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.UserRepository;
import java.util.Date;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;




@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class OrderRepositoryTest {
    
    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    
    
    @Test
    public void createOrder_success() {
        // given
        
        Order order = new Order();
        order.setPrice(30000);
        order.setUserId(1);
        order.setDay(java.sql.Date.valueOf(MyDateFormat.convertTo_yyyy_MM_dd(new Date())));
        
        
        orderRepository.save(order);
        
        Order found = orderRepository.findOrderByUserIdAndDay(order.getUserId(), order.getDay());
        
        Assertions.assertThat(order.getPrice()==found.getPrice());
        
        
        
    }
    
    
//    @Test
//    public void findOrder_InMonth_ByUserID() {
//        // given
//        User user = new User();
//        user.setEmail("test@");
//        user.setPassword("123456");
//        user.setName("Nguyễn Ngọc Bảo");
//        
//        userRepository.save(user);
//        
//        User found = userRepository.findByEmail(user.getEmail());
//        
//        Assertions.assertThat(user.getName()).isEqualTo(found.getName());
//        
//    }
    
}
