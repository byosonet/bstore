package com.bstore.services.persistence.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bstore.services.persistence.pojo.Editorial;
import com.bstore.services.persistence.utils.HibernateUtil;

/**
 * 
 * @author hustler
 *
 */
public class EditorialDaoImpl extends HibernateDaoSupport implements EditorialDao {
	private static Logger logger = Logger.getLogger(EditorialDaoImpl.class);

	
	public Editorial getEditorial(int id) {
		logger.info("Buscando editorial by id:: "+id);
        
		return (Editorial) HibernateUtil.getSessionFactory()
                .createQuery("FROM Editorial e WHERE e.id = :id")
                .setParameter("id", id)
                .uniqueResult();
	}

	public void saveOrUpdate(Editorial editorial) {
		logger.info("saveOrUpdateEditorial: "+editorial.toString());
		HibernateUtil.getSessionFactory().saveOrUpdate(editorial);
		HibernateUtil.getSessionFactory().flush();
	}

	
	@SuppressWarnings("unchecked")
	public List<Editorial> getAll(){
		logger.info("getAll");
		return (List<Editorial>) HibernateUtil.getSessionFactory().createQuery("FROM Editorial e").list();
	}
}
