package com.bstore.services.persistence.dao;

import com.bstore.services.persistence.pojo.Fuente;

/**
 * 
 * @author hustler
 *
 */
public interface FuenteDao {
	Fuente getFuente(int id);
	void saveOrUpdateFuente(Fuente fuente);
}
