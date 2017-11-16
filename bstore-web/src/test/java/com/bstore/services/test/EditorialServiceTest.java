package com.bstore.services.test;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bstore.services.persistence.pojo.Editorial;
import com.bstore.services.service.EditorialService;

/**
 * 
 * @author hustler
 *
 */
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class EditorialServiceTest {
	Logger logger = Logger.getLogger(EditorialServiceTest.class);

	@Autowired
	private EditorialService editorialService;

	@Test
	public void testGetEditorial(){
		logger.info("testGetEditorial");

		Editorial editorial = editorialService.getEditorial(1);
		if(editorial!=null){
			logger.info("editorial.getId(): "+editorial.getId());
			logger.info("editorial.getNombre(): "+editorial.getNombre());
			logger.info("editorial.getEstatus(): "+editorial.getEstatus());
			logger.info("editorial.getRfc(): "+editorial.getRfc());
			logger.info("editorial.getTelefono(): "+editorial.getTelefono());
		}
	}

	@Test
	public void testGetAll(){
		logger.info("testGetAll");

		List<Editorial> editorialList = editorialService.getAll();
		logger.info("editorialList: "+editorialList);

		if(editorialList!=null){
			logger.info("listSize: "+editorialList.size());
		}
	}
	
	@Test
	public void testSaveOrUpdate(){
		logger.info("testSaveOrUpdate");
		Editorial editorial = this.editorialService.getEditorial(3);
		editorial.setNombre("Editorial Prueba");
		editorial.setEstatus(0);
		editorial.setEmail("pudeeperalta@gmail.com");
		editorial.setFechaUmodif(new Date());
		editorial.setRfc("EITT873687G31");
		editorialService.saveOrUpdate(editorial);
		logger.info("testSaveOrUpdate termina");
	}
}
