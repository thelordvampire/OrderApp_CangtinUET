/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.auth.service;

import com.example.demo.model.User;
import java.util.List;



public interface UserService {
    void save(User user);

    User findByEmail(String email);

    int findIdByEmail(String email);
    
    List<User> getAllUser();

    
}