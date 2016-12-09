/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.leapfrog.leatherdatabase.model;

/**
 *
 * @author apple
 */
public class Data {
    
    private String compname,products,email,contactperson,phone,mobile,fax,hallnum,stalnum,postcode,country,address;
    
    public Data(){
        
        
    }

    public Data(String compname, String products, String email, String contactperson, String phone, String mobile, String fax, String hallnum, String stalnum, String postcode, String country, String address) {
        this.compname = compname;
        this.products = products;
        this.email = email;
        this.contactperson = contactperson;
        this.phone = phone;
        this.mobile = mobile;
        this.fax = fax;
        this.hallnum = hallnum;
        this.stalnum = stalnum;
        this.postcode = postcode;
        this.country = country;
        this.address = address;
     
    }
    

    public String getCompname() {
        return compname;
    }

    public void setCompname(String compname) {
        this.compname = compname;
    }

    public String getProducts() {
        return products;
    }

    public void setProducts(String products) {
        this.products = products;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactperson() {
        return contactperson;
    }

    public void setContactperson(String contactperson) {
        this.contactperson = contactperson;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getHallnum() {
        return hallnum;
    }

    public void setHallnum(String hallnum) {
        this.hallnum = hallnum;
    }

    public String getStalnum() {
        return stalnum;
    }

    public void setStalnum(String stalnum) {
        this.stalnum = stalnum;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    
    public String toCSV() {
    return "Company=" + compname +", country= "+country+", Address="+address + ", postcode="+postcode+", phone="+phone+", mobile="+mobile+", fax="+fax+", email="+email+", contactperson="+contactperson+", hallnum="+hallnum+", stalnum="+stalnum +", products="+products+"\r\n";
} 
    
  
    
    
    
}
