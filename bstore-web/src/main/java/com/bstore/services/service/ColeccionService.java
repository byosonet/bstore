package com.bstore.services.service;

import java.util.List;

import com.bstore.services.persistence.pojo.Coleccion;

public interface ColeccionService {
	List<Coleccion> getColeccionDao(boolean tipoOrden);
	List<Coleccion> getAll();
	void saveOrUpdate(Coleccion coleccion);
}
