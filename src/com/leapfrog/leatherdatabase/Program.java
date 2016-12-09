/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.leatherdatabase;

import com.leapfrog.leatherdatabase.model.Data;
import com.leapfrog.leatherdatabase.util.RegexMatcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 *
 * @author apple
 */
public class Program {

    private static final String base_url = "http://www.iilfleatherfair.com/leatherfair/chennaileather/search.php?page=";

    public static void main(String[] args) {
        
        
        try {
            int count = 1;
            
            List<Data> datalist = new ArrayList<>();
            
            RegexMatcher regexMatcher = new RegexMatcher();
            
            for (int i = -1; i <= 16; i++) {
                String main_url = base_url + i;
                Document docs = Jsoup.connect(main_url).timeout(0).get();
                Elements allelements = docs.select("div.content_left a.ref ");
                for (Element href : allelements) {
                    String rawlink = href.attr("href");
                    String innerlink = "http://www.iilfleatherfair.com/leatherfair/chennaileather/" + rawlink;

                    Document innerdocs = Jsoup.connect(innerlink).timeout(0).get();
                    //System.out.println(innerdocs.toString());

                    Elements realdata = innerdocs.select("div.content_left table[width=100%]");
                    for (Element rawdata : realdata) {
                        StringBuilder builder = new StringBuilder();
                        String content = rawdata.html();
                        Scanner scanner = new Scanner(content);
                        while (scanner.hasNextLine()) {
                            String line = scanner.nextLine();
                            builder.append(line);
                        }
                        // System.out.println(builder.toString());

                        String regex = "Company Name.*?18\">(.*?)</span>.*?Pro.*?14\">(.*?)</p>.*?Other(.*?)Address</p>(.*?)</td>";
                        Matcher matcher = regexMatcher.match(regex, builder.toString());
                        if (matcher.find()) {
                            String CompanyName = matcher.group(1).replaceAll(",", "");
                            String Products = matcher.group(2).replaceAll(",", "");
                            String details = matcher.group(3);
                            String address = matcher.group(4).trim();
                            Data data = new Data();
                            System.out.println(CompanyName);
                            data.setCompname(CompanyName);
                            System.out.println(Products);
                            data.setProducts(Products);

                            //System.out.println(details);
                            String regex1 = "Contact Person.*?8%\">(.*?)</td>";
                            Matcher matcher1 = regexMatcher.match(regex1, details);
                            if (matcher1.find()) {
                                String ContactPerson = matcher1.group(1).replaceAll(",", "");
                                System.out.println(ContactPerson);
                                data.setContactperson(ContactPerson);
                            }else{
                                data.setContactperson(" ");
                            }
                            String regex2 = "Phone</td>\\s+<td>:</td>\\s+<td>(.*?)</td>";
                            Matcher matcher2 = regexMatcher.match(regex2, details);
                            if (matcher2.find()) {
                                String Phone = matcher2.group(1).replaceAll(",", "");
                                System.out.println(Phone);
      
                                data.setPhone(Phone);

                            }else{
                                data.setPhone(" ");
                            }
                            String regex3 = "Mobile Number </td>\\s+<td>:</td>\\s+<td>(.*?)</td>";
                            Matcher matcher3 = regexMatcher.match(regex3, details);
                            if (matcher3.find()) {
                                String MobileNumber = matcher3.group(1).replaceAll(",", "");
                                System.out.println(MobileNumber);
                                data.setMobile(MobileNumber);

                            }else{
                                data.setMobile(" ");
                            }
                            String regex4 = "Fax </td>\\s+<td>:</td>\\s+<td>(.*?)</td>";
                            Matcher matcher4 = regexMatcher.match(regex4, details);
                            if (matcher3.find()) {
                                String Fax = matcher4.group(1).replaceAll(",", "");
                                System.out.println("fax---" + Fax);
                                data.setFax(Fax);

                            }else{
                                data.setFax(" ");
                            }
                            String regex5 = "Email </td>\\s+<td>:</td>\\s+<td>.*?>(.*?)</a>";
                            Matcher matcher5 = regexMatcher.match(regex5, details);
                            if (matcher3.find()) {
                                String Email = matcher5.group(1).replaceAll(",", "");
                                System.out.println(Email);
                                data.setEmail(Email);
                            }else{
                                data.setEmail(" ");
                            }
                            String regex6 = "Hall Number </td>\\s+<td>:</td>\\s+<td>(.*?)</td>";
                            Matcher matcher6 = regexMatcher.match(regex6, details);
                            if (matcher6.find()) {
                                String HallNumber = matcher6.group(1).replaceAll(",", "");
                                System.out.println(HallNumber);
                                data.setHallnum(HallNumber);

                            }else{
                                data.setHallnum("");
                            }
                            String regex7 = "Stall Number \\(Status\\) </td>\\s+<td>:</td>\\s+<td>(.*?)</td>";
                            Matcher matcher7 = regexMatcher.match(regex7, details);
                            if (matcher7.find()) {
                                String StallNumber = matcher7.group(1);
                                System.out.println(StallNumber);
                                data.setStalnum(StallNumber);

                            }else{
                                data.setStalnum(" ");
                            }
                          
                            System.out.println(address);

                            String regex8 = "(.*?)\\(([^)]+)\\)";
                            Matcher matcher8 = regexMatcher.match(regex8,address);
                            if (matcher8.find()) {
                                String Address = matcher8.group(1);
                            //    String codereg = "\\d{6}";
                            //    Matcher matcher9 = regexMatcher.match(codereg, Address);
                             
                             
                               // String[] tokens = Address.split("\\d{6}");
                               // for(int j = 0;j<= tokens.length ; j++){
                                //    System.out.println("token["+j+"]" + tokens[j]);
                              //  }
                                String Country = matcher8.group(2);
                                
                                String[] tokens= Address.split(" ");
                                String newaddress="";
                                String postcode="";
                                for(int j = 0;j<tokens.length;j++){
                                    String codregex = "\\d{4}";
                                    Matcher matcher10 = regexMatcher.match(codregex,tokens[j]);
                                    if(matcher10.find()){
                                        postcode =  tokens[j];
                                        data.setPostcode(tokens[j]);
                                        newaddress = Address.replace(tokens[j],"").replace(Country, "").replaceAll(",", "");
                                    }
                                       if(postcode.isEmpty()){
                                           data.setPostcode(" ");
                                       }
                                    
                                }
                                
                                System.out.println("Address "+Address);
                                System.out.println("New Address-->"+ newaddress);
                                if(address.isEmpty()){
                                    data.setAddress(" ");
                                    data.setCountry(" ");
                                }
                                data.setAddress(newaddress);
                                System.out.println("Country "+Country);
                                data.setCountry(Country);
//                                while(matcher9.find()){
//                                    System.out.println("postcode---->"+matcher9.group());
//                                }
//                                if(Country.equalsIgnoreCase("brazil")){
//                                    
//                                    String[] codetokens = Country.split(" ");
//                                    for(int k = 0;k<codetokens.length;k++){
//                                        if(codetokens[k].contains("-")){
//                                            System.out.println("postcode----->"+codetokens[k]);
//                                        }
//                                    }
//                                }
                                   

                            }
                            datalist.add(data);
                        }
                        System.out.println("========" + count + "==============");
                        count++;
                        regexMatcher.writeToFile(datalist);
                    }
                }
            }
            
            

        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }

}
