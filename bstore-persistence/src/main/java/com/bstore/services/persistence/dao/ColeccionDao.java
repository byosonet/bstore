package com.bstore.services.persistence.dao;

import java.util.List;

import com.bstore.services.persistence.pojo.Coleccion;

public interface ColeccionDao {
	List<Coleccion> getColecion(boolean tipoOrden);
}
