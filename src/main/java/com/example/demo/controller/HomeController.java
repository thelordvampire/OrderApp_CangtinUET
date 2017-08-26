/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Bao
 */
@Controller
public class HomeController {
    
//    @RequestMapping("/home.html")
//    public String home() {
//        return "home";
//    }
    
    @RequestMapping("/")
    public String index() {
        return "redirect:/home.html";
    }
}