package com.bstore.services.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bstore.services.persistence.dao.CompraDao;
import com.bstore.services.persistence.pojo.Compra;
import com.bstore.services.persistence.pojo.CompraId;

/**
 * 
 * @author hustler
 *
 */
public class CompraServiceImpl implements CompraService {

	@Autowired
	private CompraDao compraDao;
	
	@Override
	public Compra getCompra(CompraId compraId) {
		return compraDao.getCompra(compraId);
	}

}
