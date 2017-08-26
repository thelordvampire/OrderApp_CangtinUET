/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.format;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Bao
 */
public class MyDateFormat {


        public static String convertTo_yyyy_MM_dd(Date date)
        {
                    
//         SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
//                    
//            Calendar cal = Calendar.getInstance();
//        System.out.println("hehehe: "+dateFormat.format(cal.getTime()));
            
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(date);
        }
        
        public static Date convertToDate(String date) throws ParseException
        {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.parse(date);
        }
    
}
