/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.DailyMenu;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author Bao
 */
public interface DailyMenuRepository extends JpaRepository<DailyMenu, Date>{
    
    DailyMenu findMenuByDay(Date day);
//    
    DailyMenu findFirstMenu_ByDay_GreaterThanOrderByDayAsc(Date day);
    
    DailyMenu findFirstMenu_ByActiveTrueAndDay_GreaterThanOrderByDayAsc(Date day);
    
    DailyMenu findFirstMenu_ByActiveTrueAndDay_LessThanOrderByDayDesc(Date day);
    
    DailyMenu findMenu_ByActiveTrueAndDay(Date day);
    
    DailyMenu findFirstMenu_ByDay_LessThanOrderByDayDesc(Date day);
    
    List<DailyMenu> findAllMenu_ByOrderByDayAsc();
    
    
    
    
       
}
