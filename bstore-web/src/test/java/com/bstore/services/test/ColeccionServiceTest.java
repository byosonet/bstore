package com.bstore.services.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bstore.services.persistence.pojo.Coleccion;
import com.bstore.services.service.ColeccionService;

/**
 * 
 * @author hustler
 *
 */
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class ColeccionServiceTest {
	private Logger logger = Logger.getLogger(ColeccionServiceTest.class);
	
	@Autowired
	private ColeccionService coleccionService;
	
	@Test
	public void testGetColeccion(){
		logger.info("testGetColeccion");
		
		List<Coleccion> coleccionList = coleccionService.getColeccionDao(true);
		if(coleccionList != null){
			logger.info("coleccionListSize(): "+coleccionList.size());
		}
	}
	
	@Test
	public void testGetAll(){
		logger.info("testGetAll");
		
		List<Coleccion> coleccionList = coleccionService.getAll();
		if(coleccionList != null){
			logger.info("coleccionListSize(): "+coleccionList.size());
		}
	}
}
