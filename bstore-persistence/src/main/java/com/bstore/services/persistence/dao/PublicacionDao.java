package com.bstore.services.persistence.dao;

import java.util.List;

import com.bstore.services.persistence.pojo.Publicacion;

public interface PublicacionDao {
	Publicacion getPublicacion(int idPublicacion);
	List<Publicacion> getPublicaciones(int idColeccion);
	List<Publicacion> getAll();
	void saveOrUpdate(Publicacion publicacion);
}
