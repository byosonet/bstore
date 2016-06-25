package com.bstore.services.persistence.dao;

import java.util.List;

import com.bstore.services.persistence.pojo.Fuente;

/**
 * 
 * @author hustler
 *
 */
public interface FuenteDao {
	Fuente getFuente(int id);
	List<Fuente> getAll();
	void saveOrUpdateFuente(Fuente fuente);
}
