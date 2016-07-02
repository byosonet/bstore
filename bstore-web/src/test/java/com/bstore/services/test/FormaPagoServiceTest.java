package com.bstore.services.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bstore.services.persistence.pojo.FormaPago;
import com.bstore.services.service.FormaPagoService;

/**
 * 
 * @author hustler
 *
 */
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class FormaPagoServiceTest {
	Logger logger = Logger.getLogger(FormaPagoServiceTest.class);
	
	@Autowired
	private FormaPagoService formaPagoService;
	
	@Test
	public void testGetFormaPago(){
		logger.info("testGetFormaPago");
		FormaPago formaPago = formaPagoService.getFormaPago(1);
		
		if(formaPago != null){
			logger.info("id: "+formaPago.getId());
			logger.info("nombre: "+formaPago.getFormaPago());
		}
	}
	
	@Test
	public void testGetAll(){
		logger.info("testGetAll");
		List<FormaPago> formaPagoList = formaPagoService.getAll();
		
		if(formaPagoList != null){
			logger.info("list.size(): "+formaPagoList.size());
		}
	}
	
	@Test
	public void testSaveOrUpdate(){
		logger.info("testSaveOrUpdate");
		
		FormaPago formaPago = new FormaPago();
		formaPago.setId(10);
		formaPago.setFormaPago("Nueva forma de pago");
		formaPago.setStatus(1);
		
		formaPagoService.saveOrUpdate(formaPago);
		logger.info("testSaveOrUpdate termina");
	}
}
