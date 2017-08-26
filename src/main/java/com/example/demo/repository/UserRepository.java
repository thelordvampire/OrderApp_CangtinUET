/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.repository;

import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 *
 * @author Bao
 */
public interface UserRepository extends JpaRepository<User, Integer>{
    
    User findByEmail(String q);
    
    @Query("select u.id from user u where u.email=?1")
    int findIdByEmail(String email);
    
}
