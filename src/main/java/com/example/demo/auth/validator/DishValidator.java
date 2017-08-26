/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.auth.validator;

import com.example.demo.model.Dish;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Bao
 */
@Component
public class DishValidator implements Validator{
    
    @Override
    public boolean supports(Class<?> clazz) {
         return clazz.equals(Dish.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        Dish dish = (Dish) target;
        
        if(dish!=null)
        {
            if(dish.getName()==null || dish.getName().isEmpty())
                errors.rejectValue("name", "error.dish.name.null");
        }
    }
    
    
    
}
