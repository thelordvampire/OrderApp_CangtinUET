/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dishgroup")
public class DishGroup implements Serializable {

    private static final long serialVersionUID = 9045175968151209605L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    
//    @OneToMany(mappedBy = "dishgroup")
            
//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    @JoinColumn(name = "id", referencedColumnName = "dishgroup_id")
//    List<Dish> dishes;
//
//    public List<Dish> getDishes() {
//        return dishes;
//    }
//
//    public void setDishes(List<Dish> dishes) {
//        this.dishes = dishes;
//    }
    
    
    
    @Column(name = "name")
    private String name;

    public DishGroup() {
    }

    public DishGroup(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
