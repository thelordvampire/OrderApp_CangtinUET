/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;


@Entity(name = "order")
@Table(name = "`order`",  uniqueConstraints=@UniqueConstraint(columnNames={"menu_day", "user_id"}))
public class Order implements Serializable{
    
    private static final long serialVersionUID = 6560329914946192668L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    
    @Column(name="menu_day", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date day;
    
    @Column(name = "user_id", nullable = false)
    private int userId;
    
    @Column(name = "price")
    private int price;
    
    @Column(name = "infor")
    private String infor;
    
    @Column(name = "rate")
    private int rate;
    
    @Transient
    private boolean canRate;
    
    @JsonIgnore
    @ManyToOne(targetEntity = User.class)
//    @ManyToOne
    @JoinColumn(name = "user_id")
    @MapsId("id")
    private User user;
    
    @ManyToOne(targetEntity = DailyMenu.class)
//    @ManyToOne
    @JoinColumn(name = "menu_day")
    @MapsId("menu_day")
    private DailyMenu menu;
    
    @ManyToMany(targetEntity = Dish.class,
//            cascade = {CascadeType.PERSIST, CascadeType.DETACH}, 
            fetch = FetchType.EAGER)
    @JoinTable(name = "order_dish", joinColumns = {
                @JoinColumn(name = "order_id", nullable = false, updatable = false) },
                inverseJoinColumns = {@JoinColumn(name = "dish_id", nullable = false, updatable = false) })
    @Fetch(value = FetchMode.SUBSELECT)
    
    private List<Dish> dishes;

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public boolean isCanRate() {
        return canRate;
    }

    public void setCanRate(boolean canRate) {
        this.canRate = canRate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public Order() {
    }

    public Order(int id, Date day, int userId, int price, String infor) {
        this.id = id;
        this.day = day;
        this.userId = userId;
        this.price = price;
        this.infor = infor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DailyMenu getMenu() {
        return menu;
    }

    public void setMenu(DailyMenu menu) {
        this.menu = menu;
    }

    @Override
    public String toString() {
        return "order: \n day: "+day;
    }
    
    

       
    
    
    
    
    
    
}
