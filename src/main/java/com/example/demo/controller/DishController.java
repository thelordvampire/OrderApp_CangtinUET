/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import com.example.demo.auth.service.DishService;
import com.example.demo.model.Dish;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Bao
 */
@Controller
public class DishController {
    
    @Autowired
    private DishService dishService;
    
    @RequestMapping(value = "/dish/fast/{dishID}", method = RequestMethod.GET)
    public String getDish(@PathVariable(value = "dishID") int dishID,
            Model model)
    {
        model.addAttribute("dish", dishService.getDishByID(dishID));
        return "dish/fast_dish_detail ::fast_dish_detail";
    }
    
    @RequestMapping(value = "/dish/autocomplete", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllDishName()
    {
        List<Dish> dishes = dishService.getAllDish();
        List<Dish> dishNames = new ArrayList<>();
        
//        System.out.println("autocomplete");
        dishes.forEach(dish->{
            Dish d = new Dish();
            d.setId(dish.getId());
            d.setName(dish.getName());
            d.setImage_link_to_show(dish.getImage_link_to_show());
            dishNames.add(d);
//            System.out.println(dish.getName());
        });
        
//        dishNames.add("Ga ran");

//        dishNames.add("Rau xao");
//        dishNames.add("Bap cai");
        
//        System.out.println("auto complete done");

        return new ResponseEntity<>(dishNames, HttpStatus.OK);
    }
    
}
