 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.ArrayList;
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
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "dish")
public class Dish implements Serializable {
    
    private static final long serialVersionUID = 7301038132838324818L;
    
    @ManyToOne
    @JoinColumn(name = "dishgroup_id")
    private DishGroup dish_group;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    
    @Column(name = "name", nullable = false)
    private String name;
    
    @Column(name = "image_path")
    private String image_path="/image/dish/";
    
    @Transient
    private String image_link_to_show;
    
    @Column(name = "infor")
    private String infor;
    
    @Transient
    private String fileError;

    public String getFileError() {
        return fileError;
    }

    public void setFileError(String fileError) {
        this.fileError = fileError;
    }
    
    @Transient
    private int countInMenu;

    public int getCountInMenu() {
        return countInMenu;
    }

    public void setCountInMenu(int countInMenu) {
        this.countInMenu = countInMenu;
    }
    
    public void increaseCountInMenu() {
        countInMenu++;
    }

    @Override
    public String toString() {
        return "name : "+name+" infor : "+infor+" image_path : "+image_path+" image link to show : "+image_link_to_show;
    }

    public String getImage_link_to_show() {
        return image_link_to_show;
    }

    public void setImage_link_to_show(String image_link_to_show) {
        this.image_link_to_show = image_link_to_show;
    }
    
    @JsonIgnore
    @ManyToMany(targetEntity = DailyMenu.class, mappedBy = "dishes", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
//    @JoinTable(name = "dailymenu_dish")
//    ", joinColumns = {
//			@JoinColumn(name = "dish_id", nullable = false, updatable = false) })
    private List<DailyMenu> menus;

    public Dish() {
    }
    
    public Dish(int id) {
        this.id = id;
    }

    public Dish(int id, String name, String image_path, String infor) {
        this.id = id;
        this.name = name;
        this.image_path = image_path;
        this.infor = infor;
    }

    public List<DailyMenu> getMenus() {
        return menus;
    }

    public void setMenus(List<DailyMenu> menus) {
        this.menus = menus;
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

    public String getImage_path() {
        return image_path;
    }

    public void setImage_path(String image_path) {
        this.image_path = image_path;
    }

    public String getInfor() {
        return infor;
    }

    public void setInfor(String infor) {
        this.infor = infor;
    }

    public DishGroup getDish_group() {
        return dish_group;
    }

    public void setDish_group(DishGroup dish_group) {
        this.dish_group = dish_group;
    }
    
    public static List<Dish> toListDish(List<Integer> dishIDs)
    {
        List<Dish> dishes = new ArrayList<>();
        dishIDs.forEach((id) -> {
            dishes.add(new Dish(id));
        });
        
        return dishes;
    }
    
}
