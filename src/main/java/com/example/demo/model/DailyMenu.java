/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "dailymenu")
public class DailyMenu implements Serializable {

    private static final long serialVersionUID = -2639587142026974345L;
    
    @Id
    @Column(name="menu_day")
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE, pattern = "yyyy-MM-dd")
    private Date day;
    
    @Column(name="active")
    boolean active;
    
    @ManyToMany(targetEntity = Dish.class, cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "dailymenu_dish", joinColumns = {
			@JoinColumn(name = "menu_day", nullable = false, updatable = false) },
                inverseJoinColumns = {@JoinColumn(name = "dish_id", nullable = false, updatable = false) })
    private List<Dish> dishes;
    
    
    @OneToMany(mappedBy = "menu", targetEntity = Order.class)
    private List<Order> orders;

    public DailyMenu() {
    }

    public DailyMenu(Date day, boolean active) {
        this.day = day;
        this.active = active;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        
        String str="";
        for (Dish dish : dishes) 
            str+=dish.toString()+"\n";
        
        return "day : "+day+" activate : "+active+"\n"+str;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    
}
