/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.leatherdatabase.util;

import com.leapfrog.leatherdatabase.model.Data;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author apple
 */
public class RegexMatcher {
    
    public Matcher match(String regex, String content){
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(content);
        
    }
    
    public void writeToFile(List<Data> datalist ){
        try{
            FileWriter writer = new FileWriter("/users/apple/desktop/test1.csv");
            StringBuilder builder = new StringBuilder();
            
            for(Data d : datalist){
                builder.append(d.toCSV());
            }
            writer.write(builder.toString());
            System.out.println("data writtern");
            writer.close();
        }catch(IOException e){
            System.out.println(e.getMessage());
        }
    }
    
}
