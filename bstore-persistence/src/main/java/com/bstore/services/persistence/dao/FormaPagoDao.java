package com.bstore.services.persistence.dao;

import java.util.List;

import com.bstore.services.persistence.pojo.FormaPago;

/**
 * 
 * @author hustler
 *
 */
public interface FormaPagoDao {
	FormaPago getFormaPago(int id);
	List<FormaPago> getFormaPagoList();
}