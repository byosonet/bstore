package com.bstore.services.service;

import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.CompraId;

public interface CompraService {
	Compra getCompra(CompraId compraId);
}
