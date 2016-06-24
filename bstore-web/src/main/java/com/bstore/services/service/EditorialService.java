package com.bstore.services.service;

import com.bstore.services.persistence.pojo.Editorial;

/**
 * 
 * @author hustler
 *
 */
public interface EditorialService {
	Editorial getEditorial(int id);
	void saveOrUpdate(Editorial editorial);
}
