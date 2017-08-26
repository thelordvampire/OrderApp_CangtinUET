/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.auth.service;

import com.example.demo.model.Dish;
import java.util.List;

/**
 *
 * @author Bao
 */
public interface DishService {
    
    Dish getDishByID(int id);
    
    List<Dish> getAllDish();
    
    Dish createDish(Dish dish);
    Dish updateDish(Dish dish);
    
    
}
