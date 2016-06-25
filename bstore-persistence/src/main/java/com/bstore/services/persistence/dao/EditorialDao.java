package com.bstore.services.persistence.dao;

import java.util.List;

import com.bstore.services.persistence.pojo.Editorial;

/**
 * 
 * @author hustler
 *
 */
public interface EditorialDao {
	Editorial getEditorial(int id);
	void saveOrUpdate(Editorial editorial);
	List<Editorial> getAll();
}
