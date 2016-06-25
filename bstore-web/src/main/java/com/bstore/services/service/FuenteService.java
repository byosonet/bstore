package com.bstore.services.service;

import java.util.List;

import com.bstore.services.persistence.pojo.Fuente;

/**
 * 
 * @author hustler
 *
 */
public interface FuenteService {
	Fuente getFuente(int id);
	void saveOrUpdateFuente(Fuente fuente);
	List<Fuente> getAll();
}
