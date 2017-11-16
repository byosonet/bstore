package com.bstore.services.service;

import java.util.List;

import com.bstore.services.persistence.pojo.FormaPago;

/**
 * 
 * @author hustler
 *
 */
public interface FormaPagoService {
	FormaPago getFormaPago(int id);
	List<FormaPago> getAll();
	void saveOrUpdate(FormaPago formaPago);
}
