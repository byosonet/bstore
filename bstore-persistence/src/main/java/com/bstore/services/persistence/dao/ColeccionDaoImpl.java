package com.bstore.services.persistence.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Transaction;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bstore.services.persistence.pojo.Coleccion;
import com.bstore.services.persistence.utils.HibernateUtil;

public class ColeccionDaoImpl extends HibernateDaoSupport implements ColeccionDao{
	private final Logger logger = Logger.getLogger(ColeccionDaoImpl.class);
	
	@SuppressWarnings("unchecked")
	public List<Coleccion> getColecion(boolean tipoOrden) {
		this.logger.info("Buscando colecciones");
		String order="";
		if(tipoOrden){
			order = "desc";
		}else{
			order = "asc";
		}
		return (List<Coleccion>) 
				 HibernateUtil.getSessionFactory()
				.createQuery("FROM Coleccion c WHERE c.estatus = :estatus order by c.nombre")
				.setParameter("estatus", 1)
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Coleccion> getAll(){
		logger.info("getAll colecciones");

		return (List<Coleccion>) HibernateUtil.getSessionFactory()
				.createQuery("FROM Coleccion c")
				.list();
	}

	public void saveOrUpdate(Coleccion coleccion) {
		try{
			Transaction tx = HibernateUtil.beginTransaction();
			logger.info("saveOrUpdate coleccion: "+coleccion.toString());
			HibernateUtil.getCurrentSession().saveOrUpdate(coleccion);
			HibernateUtil.getCurrentSession().flush();
			tx.commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			HibernateUtil.getCurrentSession().close();
		}
	}
}
