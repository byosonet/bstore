package com.bstore.services.service;

import com.bstore.services.model.MenuModel;
import java.util.List;

import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.CompraId;
import com.bstore.services.persistence.pojo.Publicacion;

public interface CompraService {
	Compra getCompra(CompraId compraId);
	List<MenuModel> getMenuColeccion(int idUsuario);
	List<Publicacion> ultimasCompras(int idUsuario);
	void crearCompra(Compra compra);
	List<Compra> obtenetComprasbyUsuario(int idUsuario);
}
