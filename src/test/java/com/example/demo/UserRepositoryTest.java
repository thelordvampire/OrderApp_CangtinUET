/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author Bao
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations="classpath:test.properties")
public class UserRepositoryTest {
    
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void createUser_success(){
        
        User user = new User();
        user.setEmail("test@");
        user.setPassword("123456");
        user.setName("Nguyễn Ngọc Bảo");
        
        userRepository.save(user);
        
        User found = userRepository.findByEmail(user.getEmail());
        
        Assertions.assertThat(user.getName()).isEqualTo(found.getName());
    
    }
    
}
