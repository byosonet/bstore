package com.bstore.services.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.apache.log4j.Logger;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 
 * @author hustler
 *
 */
@ContextConfiguration(locations = { "classpath:applicationContextTest.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
public class FuenteServiceTest {
	Logger logger = Logger.getLogger(FuenteServiceTest.class);
	
	@Test
	public void testGetFuente(){
		logger.info("testGetFuente");
	}
}