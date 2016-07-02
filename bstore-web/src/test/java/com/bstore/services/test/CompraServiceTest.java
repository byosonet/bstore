package com.bstore.services.test;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bstore.services.persistence.pojo.Coleccion;
import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.CompraId;
import com.bstore.services.persistence.pojo.Publicacion;
import com.bstore.services.service.CompraService;

/**
 * 
 * @author hustler
 *
 */
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class CompraServiceTest {
	private Logger logger = Logger.getLogger(CompraServiceTest.class);
	
	@Autowired
	private CompraService compraService;
	
	@Test
	public void testGetCompra(){
		logger.info("testGetCompra");
		
		CompraId compraId = new CompraId();
		compraId.setIdPublicacion(1);
		compraId.setIdUsuario(1);
		
		Compra compra = compraService.getCompra(compraId);
		
		if(compra != null){
			logger.info("Se encontró la compra...");
		}else{
			logger.info("La compra no ha podido ser hallada...");
		}
	}
	
	@Test
	public void testGetMenuColeccion(){
		logger.info("testGetMenuColeccion");
		
		int userId = 1;
		
		Map<Coleccion, List<Publicacion>> menuColeccion = compraService.getMenuColeccion(userId);
		if(menuColeccion != null){
			logger.info("Se encontró el menu de las colecciones del usuario con id: "+userId);
		}else{
			logger.info("No se pudo encontrar el menu de las colecciones del usuario con id: "+userId);
		}
	}
}
