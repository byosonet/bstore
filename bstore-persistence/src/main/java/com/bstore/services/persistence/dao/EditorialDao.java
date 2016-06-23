package com.bstore.services.persistence.dao;

import com.bstore.services.persistence.pojo.Editorial;

/**
 * 
 * @author hustler
 *
 */
public interface EditorialDao {
	Editorial getEditorial(int id);
	void saveOrUpdate(Editorial editorial);
}
