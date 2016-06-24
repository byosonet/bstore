package com.bstore.services.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bstore.services.persistence.dao.EditorialDao;
import com.bstore.services.persistence.pojo.Editorial;

public class EditorialServiceImpl implements EditorialService {

	@Autowired
	private EditorialDao editorialDao;
	
	@Override
	public Editorial getEditorial(int id) {
		return editorialDao.getEditorial(id);
	}

	@Override
	public void saveOrUpdate(Editorial editorial) {
		editorialDao.saveOrUpdate(editorial);
	}

}
