package com.example.demo.thang.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.auth.service.RoleService;
import com.example.demo.auth.service.UserService;
import com.example.demo.auth.validator.UserValidator;
import com.example.demo.model.User;
import org.springframework.validation.BindingResult;

@Controller
public class AddUserController {
	
    @Autowired
    private UserService userService;
	
    @Autowired
    private RoleService roleService;
	
    @RequestMapping(value = "/admin/user", method = RequestMethod.GET)
    public String showAll(Model model)
    {
    	List<User> users = userService.getAllUser();
        model.addAttribute("users", users);
//        model.addAttribute("page","user_show_all");
        return "user/user_show_all";
    }
	
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.GET)
    public String createUser(Model model)
    {
        model.addAttribute("user", new User());
        model.addAttribute("roles", roleService.getAll());
        model.addAttribute("page","user_create");
        return "user/user_create";
    }
	
    @RequestMapping(value = "/admin/user/create", method = RequestMethod.POST)
    public String createUser(Model model,@ModelAttribute("user") User user,
            BindingResult result, UserValidator userValidator)
    {
//		System.out.print("create user "+user.getName());
//        model.addAttribute("page","user_create");
        if(result.hasErrors())
        {
            
        }
        else
        {
            userService.save(user);
        }
        
        return "redirect:/admin/user";
    }
	
}
