/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Bao
 */
@Controller
public class ImageController {
    
    @RequestMapping(value = "/image/dish/{imageFolder}")
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "imageFolder") 
            String imageFolder) throws IOException {
//        System.out.println("image image vao day roi vao day roi");
        File folder = new File("src/main/resources/static/image/dish/" + imageFolder);
        
        File fileImage = new File("src/main/resources/static/image/no_image.png");
        for(File file: folder.listFiles())
            if(file.getName().contains("default"))
            {
                fileImage = file;
                break;
            }

        return Files.readAllBytes(fileImage.toPath());
    }
    
}


