package com.bstore.services.service;

import java.util.List;
import java.util.Map;

import com.bstore.services.persistence.pojo.Coleccion;
import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.CompraId;
import com.bstore.services.persistence.pojo.Publicacion;

public interface CompraService {
	Compra getCompra(CompraId compraId);
	Map<Coleccion, List<Publicacion>> getMenuColeccion(int idUsuario);
	List<Publicacion> ultimasCompras(int idUsuario);
}
