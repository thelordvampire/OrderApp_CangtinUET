package com.example.demo.thang.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.auth.service.DishService;
import com.example.demo.auth.validator.DailyMenuValidator;
import com.example.demo.auth.validator.DishValidator;
import com.example.demo.format.MyImageFormat;
import com.example.demo.model.Dish;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class DishController1 {
    
    @Autowired
    private DishService dishService;
    
    @RequestMapping(value = "/admin/dish", method = RequestMethod.GET)
    public String showAll(Model model)
    {
    	List<Dish> dishes = dishService.getAllDish();
        model.addAttribute("dishes", dishes);
        return "dish/dish_show_all";
    }
//    @RequestMapping(value = "/admin/dish/create", method = RequestMethod.GET)
//    public String Create(Model model){
//        model.addAttribute("page","dish_create");
//        return "user/admin";
//    }
    @GetMapping("/admin/dish/create")
    public String create(Model model) {
        model.addAttribute("new_dish", new Dish());
        return "dish/dish_create";
    }
    
    @RequestMapping(value="/admin/dish/create", method = RequestMethod.POST) // //new annotation since 4.3
    public String create(@RequestParam("file") MultipartFile file,
                        @ModelAttribute("new_dish") Dish newDish, 
                        Model model, BindingResult result, 
                        DishValidator dishValidator) {
    	
//        System.out.print("image image image");
//    	System.out.print(""+newDish.getName());
//    	System.out.print(""+newDish.getInfor());
//    	System.out.print(""+newDish.getImage_path());
    	
        if(file==null || file.isEmpty())
            result.rejectValue("fileError", "error.dish.image.null");
        
        dishValidator.validate(newDish, result);
        
        if(result.hasErrors())
        {
            model.addAttribute("new_dish", newDish);
            model.addAttribute("error", "yes");
            return "dish/dish_create";
        
        }
        else
        {
            Dish createdDish = dishService.createDish(newDish);
            try {
                MyImageFormat.saveImage(createdDish.getImage_path(), createdDish.getId(), file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    	
        return "redirect:/admin/dish";
    }
    
    @PostMapping("/admin/dish/update")
    public String update(Model model, 
            @RequestParam("dishID") int dishID)
    {
        model.addAttribute("dish", dishService.getDishByID(dishID));
        return "dish/dish_update";
    }
    
    @PostMapping("/admin/dish/update_action")
    public String update(@RequestParam("file") MultipartFile file,
                        @ModelAttribute("dish") Dish dish, 
                        Model model, BindingResult result, 
                        DishValidator dishValidator) 
    {
        dishValidator.validate(dish, result);
        
        System.out.println("update dish id : "+dish.getId());
        
        if(result.hasErrors())
        {
            model.addAttribute("dish", dish);
            model.addAttribute("error", "yes");
            return "dish/dish_update";
        }
        else
        {
            Dish updatedDish = dishService.updateDish(dish);
            if(file!=null && !file.isEmpty())
            {
                try {
                    MyImageFormat.saveImage(updatedDish.getImage_path(), updatedDish.getId(), file);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    	
        return "redirect:/admin/dish";
    }
    
}

