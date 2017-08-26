/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.auth.service.impl;

import com.example.demo.auth.service.DailyMenuService;
import com.example.demo.format.MyImageFormat;
import com.example.demo.model.DailyMenu;
import com.example.demo.repository.DailyMenuRepository;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DailyMenuServiceImpl implements DailyMenuService {
    
    @Autowired
    private DailyMenuRepository menuRepository;

    @Override
    public void save(DailyMenu menu) {
        menuRepository.save(menu);
    }

    @Override
    public List<DailyMenu> getAllMenu() {
        
        List<DailyMenu> menus = menuRepository.findAllMenu_ByOrderByDayAsc();
        if(menus!=null && menus.isEmpty()==false)
        {
            menus.forEach(menu ->{
                menu.getDishes().forEach(dish->{
                    dish.setImage_link_to_show(MyImageFormat.convertImagePath(dish.getImage_path(), dish.getId()));
                });
            });
        }
        return menus;
    }
    
    

    @Override
    public void activate(DailyMenu menu) {
        menuRepository.save(menu);
    }
    
   

    @Override
    public void deactivate(DailyMenu menu) {
        menuRepository.save(menu);
    }

    @Override
    public DailyMenu findMenuByDay(Date day) {
        DailyMenu menu =  menuRepository.findMenuByDay(day);
        if(menu!=null)
            menu.getDishes().forEach(dish->{
                dish.setImage_link_to_show(MyImageFormat.convertImagePath(dish.getImage_path(), dish.getId()));
            });
        
        return menu;
    }

    @Override
    public DailyMenu findByCurrentDay() {
//        Calendar cal = Calendar.getInstance();
//        cal.add(Calendar.DATE, 1);
//        return menuRepository.findByDay(cal.getTime());

        return null;
    }
    
    @Override
    public DailyMenu findAvaiableMenu_ByDay(Date day)
    {
        DailyMenu menu = menuRepository.findMenu_ByActiveTrueAndDay(day);
        if(menu!=null)
        {
            menu.getDishes().forEach(dish->{
                dish.setImage_link_to_show(MyImageFormat.convertImagePath(dish.getImage_path(), dish.getId()));
            });
        }
        
        return menu;
    }

    @Override
    public DailyMenu findAvaiableMenu_ByNextDay(Date day) {
        DailyMenu menu = menuRepository.findFirstMenu_ByActiveTrueAndDay_GreaterThanOrderByDayAsc(day);
        if(menu!=null)
        {
            menu.getDishes().forEach(dish->{
                dish.setImage_link_to_show(MyImageFormat.convertImagePath(dish.getImage_path(), dish.getId()));
            });
        }
        
        return menu;
    }

    @Override
    public DailyMenu findAvaiableMenu_ByPreviousDay(Date day) 
    {
        DailyMenu menu = menuRepository.findFirstMenu_ByActiveTrueAndDay_LessThanOrderByDayDesc(day);
        if(menu!=null)
        {
            menu.getDishes().forEach(dish->{
                dish.setImage_link_to_show(MyImageFormat.convertImagePath(dish.getImage_path(), dish.getId()));
            });
        }
        
        return menu;
    }
    
    

    @Override
    public DailyMenu findMenu_ByNextDay(Date day) {
        
        DailyMenu menu = menuRepository.findFirstMenu_ByDay_GreaterThanOrderByDayAsc(day);
        if(menu!=null)
        {
            menu.getDishes().forEach(dish->{
                dish.setImage_link_to_show(MyImageFormat.convertImagePath(dish.getImage_path(), dish.getId()));
            });
        }
        
        return menu;
    }

    @Override
    public DailyMenu findMenu_ByPreviousDay(Date day) {

        DailyMenu menu = menuRepository.findFirstMenu_ByDay_LessThanOrderByDayDesc(day);
        if(menu!=null)
        {
            menu.getDishes().forEach(dish->{
                dish.setImage_link_to_show(MyImageFormat.convertImagePath(dish.getImage_path(), dish.getId()));
            });
        }
        
        return menu;
    }

    
    
    
    
    
    
}
