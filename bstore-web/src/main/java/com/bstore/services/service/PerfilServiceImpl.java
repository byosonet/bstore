package com.bstore.services.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public Perfil getPerfil(int id) {
		logger.info("getPerfil; id: "+id);
		return perfilDao.getPerfil(id);
	}

	@Override
	@Transactional
	public List<Perfil> getAll() {
		logger.info("getAll");
		return perfilDao.getAll();
	}

	@Override
	@Transactional
	public void saveOrUpdate(Perfil perfil) {
		logger.info("saveOrUpdate, perfil a guardar es: "+perfil.toString());
	}

}
