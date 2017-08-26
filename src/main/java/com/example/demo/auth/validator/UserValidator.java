/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.auth.validator;

import com.example.demo.auth.service.UserService;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;



@Component
public class UserValidator implements Validator{
    
    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        
        User user = (User) o;
         
        if(user.getEmail().isEmpty())
            errors.rejectValue("email", "error.user.email.null");
         
        if(user.getPassword().isEmpty())
            errors.rejectValue("password", "error.user.password.null");
         
        if(!user.getRepassword().equals(user.getPassword()))
            errors.rejectValue("repassword", "error.user.repassword.diff"); 

        if(user.getName().isEmpty())
            errors.rejectValue("name", "error.user.name.null");
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "NotEmpty");
//        
//        if (userService.findByEmail(user.getEmail()) != null) {
//            errors.rejectValue("email", "email đã tồn tại");
//        }
//
//        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "NotEmpty");
//        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
//            errors.rejectValue("password", "Size.userForm.password");
//        }
//
//        if (!user.getRepassword().equals(user.getPassword())) {
//            errors.rejectValue("passwordConfirm", "Nhập lại mật khẩu không đúng");
//        }
    }
    
}
