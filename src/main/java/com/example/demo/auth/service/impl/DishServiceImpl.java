/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.auth.service.impl;

import com.example.demo.auth.service.DishService;
import com.example.demo.format.MyImageFormat;
import com.example.demo.model.Dish;
import com.example.demo.repository.DishRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bao
 */
@Service
public class DishServiceImpl implements DishService{
    
    @Autowired
    DishRepository dishRepository;
    
    @Override
    public Dish createDish(Dish dish)
    {
    	return dishRepository.save(dish);
    }
    
    @Override
    public Dish updateDish(Dish dish) {
        return dishRepository.save(dish);
    }

    @Override
    public Dish getDishByID(int id) {
        Dish dish = dishRepository.findOne(id);
        if(dish!=null)
            dish.setImage_link_to_show(MyImageFormat.convertImagePath(dish.getImage_path(), dish.getId()));
        return dish;
    }
    
    @Override
    public List<Dish> getAllDish() {
        
        List<Dish> dishes = dishRepository.findAll();
        if(dishes!=null)
        {
            dishes.forEach(dish->{
                dish.setImage_link_to_show(MyImageFormat.convertImagePath(dish.getImage_path(), dish.getId()));
            });
            
        }
        
        return dishes;
    }
    
}
