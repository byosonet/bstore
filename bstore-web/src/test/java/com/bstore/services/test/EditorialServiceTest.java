package com.bstore.services.test;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.bstore.services.persistence.dao.EditorialDao;
import com.bstore.services.persistence.pojo.Editorial;

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
	private EditorialDao editorialDao;

	@Test
	public void testGetEditorial(){
		logger.info("testGetEditorial");

		Editorial editorial = editorialDao.getEditorial(1);
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

		List<Editorial> editorialList = editorialDao.getAll();
		logger.info("editorialList: "+editorialList);

		if(editorialList!=null){
			logger.info("listSize: "+editorialList.size());
		}
	}
}
