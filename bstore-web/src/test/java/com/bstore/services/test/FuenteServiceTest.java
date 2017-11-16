package com.bstore.services.test;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bstore.services.persistence.pojo.Fuente;
import com.bstore.services.service.FuenteService;

/**
 * 
 * @author hustler
 *
 */
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class FuenteServiceTest {
	
	private Logger logger = Logger.getLogger(FuenteServiceTest.class);
	
	@Autowired
	private FuenteService fuenteService;
	
	@Test
	public void testGetFuente(){
		logger.info("testGetFuente");
		Fuente fuente = fuenteService.getFuente(2);
		
		if(fuente != null){
			logger.info("fuente.getId(): "+fuente.getId());
			logger.info("fuente.getNombreBiblioteca(): "+fuente.getNombreBiblioteca());
			logger.info("fuente.getAutor(): "+fuente.getAutor());
			logger.info("fuente.getPaginaWeb(): "+fuente.getPaginaWeb());
			logger.info("fuente.getRfc(): "+fuente.getRfc());
		}
	}
	
	@Test
	public void testSaveOrUpdate(){
		logger.info("testSaveOrUpdate");
		
		Fuente fuente = new Fuente();
		fuente.setId(100);
		fuente.setAutor("AutorPrueba");
		fuente.setEmail("pudeeperalta@gmail.com");
		fuente.setEstatus(1);
		fuente.setFechaUmodif(new Date());
		fuente.setNombreBiblioteca("BibliotecaPrueba");
		fuente.setPaginaWeb("www.prueba.com");
		fuente.setRfc("EIPP886382M53");
		fuente.setTelefono("7729273849");
		
		fuenteService.saveOrUpdateFuente(fuente);
		
		logger.info("testSaveOrUpdate termina");
	}
	
	@Test
	public void testGetAll(){
		logger.info("testGetFuente");
		List<Fuente> fuenteList = fuenteService.getAll();
		
		if(fuenteList != null){
			logger.info("fuenteList size: "+fuenteList.size());
		}
	}
}