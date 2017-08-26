/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.auth.service;

import com.example.demo.model.DailyMenu;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Bao
 */
public interface DailyMenuService {
    
    void save(DailyMenu menu);
    
    List<DailyMenu> getAllMenu();

    DailyMenu findMenuByDay(Date day);
    
    DailyMenu findAvaiableMenu_ByDay(Date day);

    DailyMenu findAvaiableMenu_ByNextDay(Date day);
    
    DailyMenu findMenu_ByNextDay(Date day);

    DailyMenu findAvaiableMenu_ByPreviousDay(Date day);

    DailyMenu findMenu_ByPreviousDay(Date day);
    
    DailyMenu findByCurrentDay();
    
    void activate(DailyMenu menu);
    
    void deactivate(DailyMenu menu);
    
}
