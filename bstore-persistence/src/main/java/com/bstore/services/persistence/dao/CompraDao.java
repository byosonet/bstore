package com.bstore.services.persistence.dao;

import java.util.List;

import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.CompraId;

public interface CompraDao {
	Compra getCompra(CompraId compraId);
	List<Compra> getComprasPorUsuario(int idUsuario);
	List<Compra> getUlrimasComprasPorUsuarioParaMenuMensajes(int idUsuario);
}
