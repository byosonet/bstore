package com.bstore.services.persistence.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bstore.services.persistence.pojo.Fuente;
import com.bstore.services.persistence.utils.TransacctionMySQL;

/**
 * 
 * @author hustler
 *
 */
public class FuenteDaoImpl extends HibernateDaoSupport implements FuenteDao {
	private final Logger logger = Logger.getLogger(FuenteDaoImpl.class);
	TransacctionMySQL mysql = new TransacctionMySQL();
	
	public Fuente getFuente(int id) {
		logger.info("getFuente, id: "+id);
		
		return (Fuente) this.getSession().createQuery("FROM Fuente f WHERE f.id = :fuenteId").setParameter("fuenteId", id).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Fuente> getAll(){
		logger.info("getAll");
		
		return (List<Fuente>) this.getSession().createQuery("FROM Fuente f").list();
	}

	public void saveOrUpdateFuente(Fuente fuente) {
		logger.info("saveOrUpdateFuente: "+fuente);
		this.getSession().saveOrUpdate(fuente);
	}
}