package com.bstore.services.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bstore.services.persistence.dao.FormaPagoDao;
import com.bstore.services.persistence.pojo.FormaPago;

/**
 * 
 * @author hustler
 *
 */
public class FormaPagoServiceImpl implements FormaPagoService {

	private Logger logger = Logger.getLogger(FormaPagoServiceImpl.class);
	
	@Autowired
	private FormaPagoDao formaPagoDao;
	
	@Override
	public FormaPago getFormaPago(int id) {
		logger.info("getformaPago; id: "+id);
		return formaPagoDao.getFormaPago(id);
	}

	@Override
	public List<FormaPago> getFormaPagoList() {
		logger.info("getFormaPagoList...");
		return formaPagoDao.getFormaPagoList();
	}

}
