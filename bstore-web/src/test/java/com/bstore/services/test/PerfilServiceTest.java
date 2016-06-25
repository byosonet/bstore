package com.bstore.services.test;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bstore.services.persistence.pojo.Perfil;
import com.bstore.services.service.PerfilService;

@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class PerfilServiceTest {
	
	private Logger logger = Logger.getLogger(PerfilServiceTest.class);
	
	@Autowired
	private PerfilService perfilService;
	
	@Test
	public void testGetPerfil(){
		logger.info("testGetPerfil");
		Perfil perfil = perfilService.getPerfil(2);
		if(perfil!=null){
			logger.info("perfil.getId(): "+perfil.getId());
			logger.info("perfil.getNombre(): "+perfil.getNombre());
			logger.info("perfil.getDescripcion(): "+perfil.getDescripcion());
			logger.info("perfil.getFechaUmodif(): "+perfil.getFechaUmodif());
		}
	}
	
	@Test
	public void testGetAll(){
		logger.info("testGetAll");
		List<Perfil> perfilList = perfilService.getAll();
		
		if(perfilList != null){
			logger.info("perfil list size(): "+perfilList.size());
		}
	}
	
	@Test
	public void testSaveOrUpdate(){
		logger.info("testSaveOrUpdate");
		Perfil perfil = new Perfil();
		perfil.setId(33);
		perfil.setDescripcion("Perfil prueba");
		perfil.setNombre("Perfil Nombre Prueba");
		perfil.setFechaUmodif(new Date());
		perfilService.saveOrUpdate(perfil);
		
		logger.info("Termina testSaveOrUpdate");
	}
}
