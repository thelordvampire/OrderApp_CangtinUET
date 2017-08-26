/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.auth.service;


public interface SecurityService {
    String findLoggedInEmail();

    void autologin(String email, String password);
}