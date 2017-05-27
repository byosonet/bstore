package com.bstore.services.persistence.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.bstore.services.persistence.pojo.Fuente;
import com.bstore.services.persistence.utils.HibernateUtil;

/**
 * 
 * @author hustler
 *
 */
public class FuenteDaoImpl extends HibernateDaoSupport implements FuenteDao {
	private final Logger logger = Logger.getLogger(FuenteDaoImpl.class);
	
	public Fuente getFuente(int id) {
		logger.info("getFuente, id: "+id);
		
		return (Fuente) HibernateUtil.getSessionFactory().createQuery("FROM Fuente f WHERE f.id = :fuenteId").setParameter("fuenteId", id).uniqueResult();
	}
	
	@SuppressWarnings("unchecked")
	public List<Fuente> getAll(){
		logger.info("getAll");
		
		return (List<Fuente>) HibernateUtil.getSessionFactory().createQuery("FROM Fuente f").list();
	}

	@Transactional
	public void saveOrUpdateFuente(Fuente fuente) {
		Transaction tx = HibernateUtil.beginTransaction();
		try{
			logger.info("saveOrUpdateFuente: "+fuente);
			HibernateUtil.getCurrentSession().saveOrUpdate(fuente);
            HibernateUtil.getCurrentSession().flush();
            tx.commit();
		} catch (HibernateException he) {
            he.printStackTrace();
            tx.rollback();
            throw he;
        } finally {
        	HibernateUtil.getCurrentSession().close();
        }
	}
}