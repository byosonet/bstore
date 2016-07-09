/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bstore.services.conekta;

import com.bstore.services.conekta.ConektaAdapter;
import com.bstore.services.conekta.service.RequestCharges;
import com.bstore.services.conekta.service.RequestChargesMSI;
import com.bstore.services.conekta.service.RequestPaymentCard;
import com.bstore.services.conekta.service.RequestPaymentCard.Details;
import com.bstore.services.conekta.service.RequestPaymentCard.Details.Item;
import com.bstore.services.conekta.service.ResponseChargesMSI;
import com.bstore.services.conekta.service.ResponseChargesMSI.Detail;
import com.bstore.services.conekta.service.ResponsePaymentCard;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author gtrejo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={
    "classpath:com/bstore/services/conekta/config/conekta-config.xml",
})
public class ConektaAdapterImplTest {
    @Autowired
    @Qualifier("conektaDev")
    private ConektaAdapter conektaAdapter;
    
    /*@Test
    public void testGetInfoCard() throws ParseException, Exception{
        System.out.println(" Comenzando TEST ... ");
        RequestPaymentCard request = new RequestPaymentCard();
        request.setAmount(4600);
        request.setCard("tok_test_visa_4242"); // tok_test_visa_4242
        request.setCurrency("MXN");
        request.setDescription("COFFINE GURUNARU");
        request.setReferenceId("BSTORE S.A de C.V");
        try {
            ResponsePaymentCard response =  this.conektaAdapter.createChargeCard(request);
            System.out.println("RESPUESTA: "+ response.toString());
            System.out.println("RESPUESTA ERROR: "+ response.getError());
        } catch (Exception e) {
            System.out.println("MENSAJE TEST: "+e.getMessage());
            e.printStackTrace();
        }
    }*/
    
    @Test
    public void testChangeMSI() throws ParseException, Exception{
        System.out.println(" Comenzando TEST - MSI... ");
        RequestPaymentCard request = new RequestPaymentCard();
        
        
        request.setAmount(5000);
        request.setCurrency("MXN");
        request.setDescription("BSTORE JUGUETE INTERACTIVO");
        request.setCard("tok_test_visa_4242");
        request.setReferenceId("9839-wolf_pack");
        Details details = new Details();
        details.setName("el men");
        details.setPhone("5500000000");
        details.setEmail("logan@x-men.org");
        Item item = new Item();
        List<Item> lista = new ArrayList<Item>();
        item.setName("publicacion num 1");
        item.setPrice("20.00");
        item.setDescription("descripcion");
        
        lista.add(item);
        details.setLine_items(lista);
        request.setDetails(details);
        
        try {
        	System.out.println("REQUEST: "+request.toString());
        	ResponsePaymentCard response =  this.conektaAdapter.createChargeCard(request);
            System.out.println("RESPONSE: "+ response.toString());
            if(response.getStatus()!=null && response.getStatus().equalsIgnoreCase("paid")){
            	System.out.println("MESSAGE RESPONSE: "+ response.getStatus().toUpperCase());
            }else{
            	System.out.println("MESSAGE ERROR: "+ response.getError().getError1());
            }
        } catch (Exception e) {
            System.out.println("MESSAGE EXCEPTION: "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static final Logger logger = Logger.getLogger(ConektaAdapterImplTest.class);
}