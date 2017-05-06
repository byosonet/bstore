package com.bstore.services.service;

import java.math.BigDecimal;
import java.util.List;

import com.bstore.services.persistence.pojo.Anexo;
import com.bstore.services.persistence.pojo.Publicacion;

public interface PublicacionService {
	List<Publicacion> getPublicacionesByColeccionID(int idColeccion, int idUsuario);
	List<Publicacion> getPublicacionesByNombreAsc(int idColeccion, int idUsuario);
	List<Publicacion> getPublicacionesByNombreDesc(int idColeccion, int idUsuario);
	List<Publicacion> getPublicacionesByPrecioAsc(int idColeccion, int idUsuario);
	List<Publicacion> getPublicacionesByPrecioDesc(int idColeccion, int idUsuario);
	List<Publicacion> getPublicacionesActivas(int idUsuario);
	Publicacion getPublicacion(int id);
	List<Publicacion> getAll();
	BigDecimal precioRealPublicacion(int idPublicacion);
	void saveOrUpdate(Publicacion publicacion);
	List<Publicacion> search(Publicacion publicacion);
	List<Anexo> buscarAnexos(int idPublicacion);
}
