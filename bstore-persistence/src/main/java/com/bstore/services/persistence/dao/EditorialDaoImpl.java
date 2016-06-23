package com.bstore.services.persistence.dao;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bstore.services.persistence.pojo.Editorial;
import com.bstore.services.persistence.utils.TransacctionMySQL;

/**
 * 
 * @author hustler
 *
 */
public class EditorialDaoImpl extends HibernateDaoSupport implements EditorialDao {
	private static Logger logger = Logger.getLogger(EditorialDaoImpl.class);
	TransacctionMySQL mysql = new TransacctionMySQL();
	
	public Editorial getEditorial(int id) {
		logger.info("Buscando editorial by id:: "+id);
        
		return (Editorial) this
                .getSession()
                .createQuery("FROM Editorial e WHERE e.id = :id")
                .setParameter("id", id)
                .uniqueResult();
	}

	public void saveOrUpdate(Editorial editorial) {
		logger.info("saveOrUpdateEditorial: "+editorial);
		this.getSession().saveOrUpdate(editorial);
	}

}
