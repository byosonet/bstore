/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bstore.services.test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.apache.commons.lang.RandomStringUtils;

/**
 *
 * @author priscila
 */
public class TestMD5 {
    
    public static void main (String args[]){
        generateToken();
    }
    
    
    
private static String generateToken(){
    MessageDigest md;
    try {
            md = MessageDigest.getInstance("MD5");
    } catch (NoSuchAlgorithmException e) {
            System.out.println("com.bstore.services.test.TestMD5.generateToken():"+e);
            throw new RuntimeException(e);
    }

    StringBuffer hexString = new StringBuffer();
    byte[] data = md.digest(RandomStringUtils.randomAlphabetic(10).getBytes());
    for (int i=0; i<data.length; i++){
            hexString.append(Integer.toHexString( (data[i]>> 4) & 0x0F ) );
            hexString.append(Integer.toHexString( data[i] & 0x0F ) );
    }
    System.out.println("com.bstore.services.test.TestMD5.generateToken():: "+hexString.toString());
    return hexString.toString();
}
    
}