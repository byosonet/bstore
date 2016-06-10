package com.bstore.services.persistence.dap;

import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.CompraId;

public interface CompraDao {
	Compra getCompra(CompraId compraId);
}
