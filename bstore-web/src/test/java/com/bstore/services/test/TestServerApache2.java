package com.bstore.services.test;

import java.io.IOException;

import javax.xml.bind.DatatypeConverter;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

public class TestServerApache2 {
	 public static void main(String[] args) {
	        try {
	            DefaultHttpClient Client = new DefaultHttpClient();
	            HttpGet httpGet = new HttpGet("http://127.0.0.1/publicaciones/documento2/");
	            String cifrado = DatatypeConverter.printBase64Binary("fulano:fulan".getBytes("UTF-8"));
	            httpGet.setHeader("Authorization", "Basic " + cifrado);
	            HttpResponse response = Client.execute(httpGet);
	            if(response!=null && response.getEntity()!=null){
	            	System.out.println("Content-Type = " + response.getEntity().getContentType());
	 	            System.out.println("Status = " + response.getStatusLine());
	 	            System.out.println("HTML = " + EntityUtils.toString(response.getEntity()));
	            }
	        } catch (IOException e) {
	        	System.out.println("No se ha podido realizar la conexion a las publicaciones: "+e.getMessage());
	            e.printStackTrace();
	        }
	    }
}

