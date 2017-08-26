/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.auth.validator;

import java.util.HashMap;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 *
 * @author Bao
 */
//@Configuration
//@ComponentScan(basePackages = { "com.example.demo.*" })
//@PropertySource(value="classpath:/com/example/demo/message.properties", ignoreResourceNotFound = true)
public class OrderValidate {
//    private Map<String, String> errors;
    
//    @Resource
//    static Environment env;
    
    public static HashMap<String, String> validate(String price, List<Integer> dishIDs, String infor)
    {
//        if(env==null)
//            System.out.println("enviroment null");
//        else
//            System.out.println("111111111111111: "+env.getProperty("abc"));
        
//        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
//        try {
//            ctx.register(OrderValidate.class);
//            ctx.refresh();
//            Environment env = ctx.getEnvironment();
//            System.out.println("Topic: " + env.getProperty("abc"));
//        } finally {
//            ctx.close();
//        }
        
        
        
        HashMap<String, String> errors= new HashMap<>();
        
        try {
            int priceInt = Integer.parseInt(price);
        }
        catch(NumberFormatException e) {
           errors.put("error.price", "Bạn chưa chọn giá tiền");
        }
        
        if(dishIDs==null || dishIDs.isEmpty())
            errors.put("error.dishes", "Bạn chưa chọn món ăn nào");
        
        return errors;
    }
    
}

