package com.bstore.services.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.bstore.services.persistence.dao.PublicacionDao;
import com.bstore.services.persistence.pojo.Publicacion;

public class PublicacionServiceImpl implements PublicacionService{
	private final Logger log = Logger.getLogger(PublicacionServiceImpl.class);
	
	@Autowired
	private PublicacionDao publicacionDao;

	@Override
	@Transactional
	public List<Publicacion> getPublicacionesByColeccionID(int idColeccion) {
		log.info("Buscando publicaciones por idColeccion: "+idColeccion);
		return this.publicacionDao.getPublicaciones(idColeccion);
	}

}
