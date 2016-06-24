package com.bstore.services.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bstore.services.persistence.dao.PerfilDao;
import com.bstore.services.persistence.pojo.Perfil;

/**
 * 
 * @author hustler
 *
 */
public class PerfilServiceImpl implements PerfilService {

	private Logger logger = Logger.getLogger(PerfilServiceImpl.class);
	
	@Autowired
	private PerfilDao perfilDao;
	
	@Override
	public Perfil getPerfil(int id) {
		logger.info("getPerfil; id: "+id);
		return perfilDao.getPerfil(id);
	}

}
