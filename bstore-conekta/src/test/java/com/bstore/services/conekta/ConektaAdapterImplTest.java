/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bstore.services.conekta;

import com.bstore.services.conekta.ConektaAdapter;
import com.bstore.services.conekta.service.RequestChargesMSI;
import com.bstore.services.conekta.service.RequestPaymentCard;
import com.bstore.services.conekta.service.ResponseChargesMSI;
import com.bstore.services.conekta.service.ResponsePaymentCard;

import java.text.ParseException;
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
        RequestChargesMSI request = new RequestChargesMSI();
        RequestChargesMSI.Card card = new RequestChargesMSI.Card();
        
        request.setAmount("600000");
        request.setCurrency("MXN");
        request.setDescription("BSTORE JUGUETE INTERACTIVO");
        request.setMonthly_installments("3");
        card.setCvc("123");
        card.setExp_month("05");
        card.setExp_year("2018");
        card.setName("BSTORE SANCHEZ");
        card.setNumber("4242424242424242");
        request.setCard(card);
        try {
            ResponseChargesMSI response =  this.conektaAdapter.createChargeCardMSI(request);
            System.out.println("RESPUESTA: "+ response.toString());
            System.out.println("RESPUESTA ERROR: "+ response.getErrors());
        } catch (Exception e) {
            System.out.println("MENSAJE TEST: "+e.getMessage());
            e.printStackTrace();
        }
    }
    
    public static final Logger logger = Logger.getLogger(ConektaAdapterImplTest.class);
}