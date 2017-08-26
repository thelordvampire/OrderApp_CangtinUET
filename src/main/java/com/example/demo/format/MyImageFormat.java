/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.format;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Bao
 */
public class MyImageFormat {
    
    public static String convertImagePath(String src, int dishID)
    {
//        File imageSource= new File("src/main/resources/static"+src+dishID);
//        
//        File[] listFiles = imageSource.listFiles();
//        for (File listFile : listFiles) {
//            if(listFile.getName().contains("default"))
//                return src+dishID+"/"+listFile.getName();
//        }
        
        return src+dishID;
    }
    
    public static void saveImage(String src, int dishID, MultipartFile image) throws IOException
    {
        	
    	File file= new File("src/main/resources/static"+src+dishID);
        
        if(!file.exists())
            file.mkdirs();
        
        if(file.isDirectory())
        {
            deleteDefaultImage(file);
        	byte[] bytes = image.getBytes();
//        	System.out.println("1111 "+image.getContentType());
//        	System.out.println("2222 "+image.getOriginalFilename());
            Path path = Paths.get(file.getPath()+ "/" + "default."+
            		image.getOriginalFilename().split("\\.")[1]);
            Files.write(path, bytes);
            
        }
    }
    
    private static void deleteDefaultImage(File file)
    {
        for(File subFile: file.listFiles())
            if(subFile.getName().contains("default"))
            {
//                System.out.println("xoa image : "+subFile.getName());
                subFile.delete();
            }
    }
    
}
