/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.auth.service.impl;

import com.example.demo.auth.service.RoleService;
import com.example.demo.model.UserRole;
import com.example.demo.repository.RoleRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Bao
 */
@Service
public class RoleServiceImpl implements RoleService{
    
    @Autowired
    RoleRepository roleRepository;

    @Override
    public List<UserRole> getAll() {
        return roleRepository.findAll();
    }
    
    
    
}
