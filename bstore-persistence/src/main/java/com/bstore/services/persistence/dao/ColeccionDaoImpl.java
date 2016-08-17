package com.bstore.services.persistence.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.bstore.services.persistence.pojo.Coleccion;
import com.bstore.services.persistence.utils.TransacctionMySQL;

public class ColeccionDaoImpl extends HibernateDaoSupport implements ColeccionDao{
	private final Logger logger = Logger.getLogger(ColeccionDaoImpl.class);
	TransacctionMySQL mysql = new TransacctionMySQL();
	
	@SuppressWarnings("unchecked")
	public List<Coleccion> getColecion(boolean tipoOrden) {
		this.logger.info("Buscando colecciones");
		String order="";
		if(tipoOrden){
			order = "desc";
		}else{
			order = "asc";
		}
		return (List<Coleccion>) this
				.getSession()
				.createQuery("FROM Coleccion c WHERE c.estatus = :estatus order by c.ranking desc")
				.setParameter("estatus", 1)
				.list();
	}

	@SuppressWarnings("unchecked")
	public List<Coleccion> getAll(){
		logger.info("getAll colecciones");

		return (List<Coleccion>) this
				.getSession()
				.createQuery("FROM Coleccion c")
				.list();
	}

	public void saveOrUpdate(Coleccion coleccion) {
		try{
			this.mysql.iniciarOperacion();
			logger.info("saveOrUpdate coleccion: "+coleccion.toString());
			this.mysql.getSesion().saveOrUpdate(coleccion);
			this.mysql.getSesion().flush();
			this.mysql.getTx().commit();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			this.mysql.getSesion().close();
		}
	}
}
