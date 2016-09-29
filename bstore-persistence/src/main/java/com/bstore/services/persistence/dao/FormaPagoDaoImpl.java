package com.bstore.services.persistence.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bstore.services.persistence.pojo.FormaPago;
import com.bstore.services.persistence.utils.HibernateUtil;

/**
 * 
 * @author hustler
 *
 */
public class FormaPagoDaoImpl extends HibernateDaoSupport implements FormaPagoDao {

	Logger logger = Logger.getLogger(FormaPagoDaoImpl.class);
	
	public FormaPago getFormaPago(int id) {
		logger.info("Buscando FormaPago by id:: "+id);
        return (FormaPago) HibernateUtil.getSessionFactory()
                .createQuery("FROM FormaPago fp WHERE fp.id = :id")
                .setParameter("id", id)
                .uniqueResult();
	}

	@SuppressWarnings("unchecked")
	public List<FormaPago> getAll() {
		logger.info("Buscando todas las formas de pago");
		return (List<FormaPago>) HibernateUtil.getSessionFactory()
                .createQuery("FROM FormaPago fp")
                .list();
	}

	public void saveOrUpdate(FormaPago formaPago) {
		logger.info("Guardar o actualizar Forma de Pago");
		HibernateUtil.getSessionFactory().saveOrUpdate(formaPago);
	}

	
}
