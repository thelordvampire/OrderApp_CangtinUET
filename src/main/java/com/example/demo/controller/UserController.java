/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.auth.service.DailyMenuService;
import com.example.demo.auth.service.RoleService;
import com.example.demo.auth.service.SecurityService;
import com.example.demo.auth.service.UserService;
import com.example.demo.auth.validator.UserValidator;
import com.example.demo.model.DailyMenu;
import com.example.demo.model.User;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private RoleService roleService;
    
    @Autowired
    private DailyMenuService menuService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;
    
    @RequestMapping(value = "/user/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("error", "Your username and password is invalid.");

        if (logout != null)
            model.addAttribute("message", "You have been logged out successfully.");

        return "user/login";
    }
    
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public String create(Model model) {

        User user = new User();
        model.addAttribute("user",user);
        model.addAttribute("roles", roleService.getAll());
        return "user/create_user";
    }
    
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String create(Model model, @RequestBody User user) {

        return "user/create_user";
    }
    
    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String admin(Model model) {
        
        DailyMenu nearestMenu = menuService.findAvaiableMenu_ByDay(new Date());
        if(nearestMenu==null )
        {
            nearestMenu = menuService.findAvaiableMenu_ByNextDay(new Date());
        }
        
        model.addAttribute("menu", nearestMenu);
        model.addAttribute("page", "default");
        return "user/admin";
    }
    
//    @Autowired
//    DailyMenuService menuService;
//    
//    @RequestMapping(value = "/admin", method = RequestMethod.GET)
//    public String admin(            Model model) {
//        if(option!=null)
//        {
//            if(option.equals("menu"))
//            {
//                model.addAttribute("menus", menuService.getAllMenu());
//                model.addAttribute("page", "menu");
//            }
//        }
//        return "user/admin";
//    }
    
//    @RequestMapping(value = "/error", method = RequestMethod.GET)
//    public String error() {
//        return "login";
//    }
    
//    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
//    public String login(Model model) {
//        model.addAttribute("error_email", "Email sai rồi");
//        return "user/login";
//    }

}
