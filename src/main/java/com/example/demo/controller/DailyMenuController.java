/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.auth.service.DailyMenuService;
import com.example.demo.auth.service.OrderService;
import com.example.demo.auth.validator.DailyMenuValidator;
import com.example.demo.model.DailyMenu;
import com.example.demo.model.Order;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Bao
 */
@Controller
public class DailyMenuController {
    
    @Autowired
    DailyMenuService menuService;
    
    @Autowired
    OrderService orderService;
    
    @RequestMapping(value = "/admin/menu/active", method = RequestMethod.POST)
    public String activateMenu(@RequestParam("menuDay")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            Date day)
    {
//        System.out.println("active : "+day);
        if(day.after(new Date()))
        {
            DailyMenu menu = menuService.findMenuByDay(day);
            if(menu!=null && menu.isActive()==false)
            {
                menu.setActive(true);
                menuService.save(menu);
            }
            
        }
        
        return "redirect:/admin/menu";
    }

    @RequestMapping(value = "/admin/menu/deactive", method = RequestMethod.POST)
    public String deactivateMenu(@RequestParam("menuDay")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            Date day)
    {
//        System.out.println("deactive");
//        System.out.println("deactive day : "+day);
        if(day.after(new Date()))
        {
//            System.out.println("deactive day : "+day);
            DailyMenu menu = menuService.findMenuByDay(day);
            if(menu!=null && menu.getOrders().isEmpty() && menu.isActive()==true)
            {
                menu.setActive(false);
                menuService.save(menu);
            }
        }
        
        return "redirect:/admin/menu";
    }
    
    @RequestMapping(value = "/admin/menu", method = RequestMethod.GET)
    public String getAllMenu(Model model)
    {
//        model.addAttribute("page", "menu_show_all");
//        menuService.getAllMenu().forEach(menu->{
//            System.out.println("hehe day : "+menu.getDay());
//        });
        model.addAttribute("menus", menuService.getAllMenu());
        return "menu/menu_show_all";
    }
    
    @RequestMapping(value = "/admin/menu/create", method = RequestMethod.GET)
    public String create(Model model)
    {
//        model.addAttribute("page", "menu_create");
        model.addAttribute("menu", new DailyMenu());
        return "menu/menu_create";
    }
    
    @RequestMapping(value = "/admin/menu/create", method = RequestMethod.POST)
    public String create(Model model, @ModelAttribute("menu") DailyMenu menu,
            BindingResult result, DailyMenuValidator menuValidator,
            RedirectAttributes redirect)
    {
//        System.out.println("menu create post");
        if(menu!=null)
        {
//            System.out.println("day : "+menu.getDay());
//            menu.getDishes().forEach(dish->{
//                System.out.println("id : "+dish.getId()+" "+dish.toString());
//            });
            
            DailyMenu findMenuByDay = menuService.findMenuByDay(menu.getDay());
            
//            System.out.println("find menu:");
//            if(findMenuByDay!=null)
//                    System.out.println(findMenuByDay.getDay());
                    
        
            if(menuService.findMenuByDay(menu.getDay())!=null)
                result.rejectValue("day", "error.menu.day.exit");
            menuValidator.validate(menu, result);
            
            if(result.hasErrors())
            {
//                model.addAttribute("page", "menu_create");
                model.addAttribute("menu", menu);
                model.addAttribute("error", "yes");
                return "menu/menu_create";
            }
            else
            {
                menuService.save(menu);
//                model.addAttribute("page", "menu_show_all");
//                model.addAttribute("menus", menuService.getAllMenu());
//                redirect.addFlashAttribute("success", "Saved contact successfully!");
                return "redirect:/admin/menu";
           }
        }
        
        return "menu/menu_create";
    }
    
    @RequestMapping(value = "/admin/menu/update", method = RequestMethod.POST)
    public String update(@RequestParam("menuDay")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            Date day, Model model)
    {
//        System.out.println("update: "+day);
        DailyMenu menu = menuService.findMenuByDay(day);
        if(menu.isActive())
            return "redirect:/admin/menu";
        
        model.addAttribute("menu", menu);
        return "menu/menu_update";
    }
    
    
    
    
    @RequestMapping(value = "/admin/menu/update_action", method = RequestMethod.POST)
    public String update(Model model, @ModelAttribute("menu") DailyMenu menu,
            BindingResult result, DailyMenuValidator menuValidator,
            RedirectAttributes redirect)
    {
        if(menu!=null)
        {
//            System.out.println("update action day : "+menu.getDay());
//            menu.getDishes().forEach(dish->{
//                System.out.println("id : "+dish.getId()+" "+dish.toString());
//            });
            
//            DailyMenu findMenuByDay = menuService.findMenuByDay(menu.getDay());
            
//            System.out.println("find menu:");
//            if(findMenuByDay!=null)
//                    System.out.println(findMenuByDay.getDay());
                    
            menuValidator.validate(menu, result);
            
            if(result.hasErrors())
            {
                model.addAttribute("menu", menu);
                model.addAttribute("error", "yes");
                return "menu/menu_update";
            }
            else
            {
                menuService.save(menu);
                return "redirect:/admin/menu";
           }
        }
        
        return "menu/menu_update";
    }
    
}
