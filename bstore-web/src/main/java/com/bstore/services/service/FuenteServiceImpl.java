package com.bstore.services.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public Fuente getFuente(int id) {
		logger.info("getFuente; id: "+id); 
		return fuenteDao.getFuente(id);
	}

	@Override
	@Transactional
	public void saveOrUpdateFuente(Fuente fuente) {
		logger.info("saveOrUpdateFuente: "+fuente.toString());
		fuenteDao.saveOrUpdateFuente(fuente);
	}

	@Override
	@Transactional
	public List<Fuente> getAll() {
		logger.info("getAll");
		return fuenteDao.getAll();
	}

}
