/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.auth.validator;

import com.example.demo.auth.service.DailyMenuService;
import com.example.demo.model.DailyMenu;
import com.example.demo.repository.DailyMenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 *
 * @author Bao
 */

@Component
@Configurable
public class DailyMenuValidator implements Validator{

//    @Autowired
//    DailyMenuService menuService;
    
//    @Autowired
//    DailyMenuRepository menuRepository;
    
    @Override
    public boolean supports(Class<?> clazz) {
         return clazz.equals(DailyMenu.class);
    }

    @Override
    public void validate(Object target, Errors errors) {
        DailyMenu menu = (DailyMenu) target;
        
        if(menu!=null)
        {
            if(menu.getDay()==null)
                errors.rejectValue("day", "error.menu.day.null");
//            else {
                
//                System.out.println("pla pla pla: "+menu.getDay());
//                if(menuService==null)
//                    System.out.println("plapla pla sao lai the");
//                DailyMenu findMenuByDay = menuService.findMenuByDay(menu.getDay());
//                System.out.println("pla pla pla: "+findMenuByDay.getDay());
//                if(findMenuByDay!=null)
//                errors.rejectValue("day", "error.menu.day.exit","abc");
//            }
            
            if(menu.getDishes()==null || menu.getDishes().size()<=0)
            {
                errors.rejectValue("dishes", "error.menu.dish.null");
            }
        }
        
    }
    
    
    
    
    
}
