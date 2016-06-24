package com.bstore.services.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bstore.services.persistence.dao.FuenteDao;
import com.bstore.services.persistence.pojo.Fuente;

/**
 * 
 * @author hustler
 *
 */
public class FuenteServiceImpl implements FuenteService {

	private Logger logger = Logger.getLogger(FuenteServiceImpl.class);
	
	@Autowired
	private FuenteDao fuenteDao;
	
	@Override
	public Fuente getFuente(int id) {
		logger.info("getFuente; id: "+id); 
		return fuenteDao.getFuente(id);
	}

	@Override
	public void saveOrUpdateFuente(Fuente fuente) {
		logger.info("saveOrUpdateFuente: "+fuente.toString());
		fuenteDao.saveOrUpdateFuente(fuente);
	}

}
