package com.bstore.services.persistence.dao;

import java.util.List;

import com.bstore.services.persistence.pojo.Publicacion;

public interface PublicacionDao {
	Publicacion getPublicacion(int idPublicacion);
	List<Publicacion> getPublicaciones(int idColeccion);
	List<Publicacion> getPublicacionesActivas();
	List<Publicacion> getAll();
	void saveOrUpdate(Publicacion publicacion);
	List<Publicacion> search(Publicacion publicacion);
	List<Publicacion> getPublicacionesPorNombreAsc(int idColeccion);
	List<Publicacion> getPublicacionesPorNombreDesc(int idColeccion);
	List<Publicacion> getPublicacionesPorPrecioAsc(int idColeccion);
	List<Publicacion> getPublicacionesPorPrecioDesc(int idColeccion);
	
}
